package kaleidot725.ashiato.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.ActivityMainBinding
import kaleidot725.ashiato.di.holder.Holder
import kaleidot725.ashiato.di.repository.LocationRepository
import kaleidot725.ashiato.di.repository.PictureRepository
import kaleidot725.ashiato.ui.contact.ContactActivity
import kaleidot725.ashiato.ui.edit.EditActivity
import kaleidot725.ashiato.ui.main.history.HistoryFragment
import kaleidot725.ashiato.ui.main.home.HomeFragment
import kaleidot725.ashiato.ui.main.settinglist.SettingListFragment
import kaleidot725.ashiato.ui.preview.PreviewActivity
import kaleidot725.ashiato.ui.setting.SettingActivity
import java.io.File
import java.io.IOException
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainNavigator, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var mainMenuSelected: Holder<MainMenu>

    @Inject
    lateinit var pictureRepository: PictureRepository

    private lateinit var viewModel: MainViewModel
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.main_actionbar)

        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        ActivityCompat.requestPermissions(this, permissions, 0)

        locationRepository.start(this)

        viewModel = ViewModelProviders.of(this, MainViewModelFactory(this)).get(MainViewModel::class.java)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        restoreMenu()
    }

    override fun onDestroy() {
        locationRepository.stop()
        super.onDestroy()
    }

    private var tempFile: File? = null
    private var imageFile: File? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (tempFile != null && imageFile != null) {
                tempFile?.copyTo(imageFile as File)
                Thread.sleep(1000 * 1)
                navigateEdit()
            }
        }
    }

    @Throws(IOException::class)
    override fun navigateCamera(): Boolean {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.putExtra("android.intent.extra.quickCapture", true)
            takePictureIntent.resolveActivity(packageManager)?.also {
                val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                tempFile = File("${storageDir}/temp.jpg")

                val photoURI: Uri =
                    FileProvider.getUriForFile(this, applicationContext.packageName + ".provider", tempFile as File)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

                this.imageFile = File(pictureRepository.took!!.path)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

        return true
    }

    override fun navigateEdit(): Boolean {
        val intent = Intent(this, EditActivity::class.java)
        startActivity(intent)
        return true
    }

    override fun navigateSetting(): Boolean {
        val intent = Intent(this, SettingActivity::class.java)
        startActivity(intent)
        return true
    }

    override fun navigateLicense(): Boolean {
        LibsBuilder()
            .withActivityTitle("License")
            .withShowLoadingProgress(false)
            .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR).start(this)
        return true
    }

    override fun navigateContact(): Boolean {
        val intent = Intent(this, ContactActivity::class.java)
        startActivity(intent)
        return true
    }

    override fun navigatePreview(): Boolean {
        val intent = Intent(this, PreviewActivity::class.java)
        startActivity(intent)
        return true
    }

    override fun navigateHome(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.main_content, HomeFragment.newInstance()).commit()
        mainMenuSelected.update(MainMenu.Home)
        return true
    }

    override fun navigateHistory(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.main_content, HistoryFragment.newInstance()).commit()
        mainMenuSelected.update(MainMenu.History)
        return true
    }

    override fun navigateSettingList(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.main_content, SettingListFragment.newInstance()).commit()
        mainMenuSelected.update(MainMenu.SettingList)
        return true
    }

    override fun navigateShare(files: List<File>) {
        val builder = ShareCompat.IntentBuilder.from(this)
        for (file in files) {
            val shareUri = FileProvider.getUriForFile(applicationContext, applicationContext.packageName + ".provider", file)
            builder.addStream(shareUri)
        }

        val intent = builder.intent
        intent.type = "image/jpg"
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    private fun restoreMenu(): Boolean {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.menu.findItem(
            when (mainMenuSelected.lastedValue) {
                MainMenu.Home -> kaleidot725.ashiato.R.id.action_home
                MainMenu.History -> kaleidot725.ashiato.R.id.action_history
                MainMenu.SettingList -> kaleidot725.ashiato.R.id.action_setting
            }
        ).setChecked(true)

        when (mainMenuSelected.lastedValue) {
            MainMenu.Home -> return navigateHome()
            MainMenu.History -> return navigateHistory()
            MainMenu.SettingList -> return navigateSettingList()
        }
    }
}
