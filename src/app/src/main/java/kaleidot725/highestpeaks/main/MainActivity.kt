package kaleidot725.highestpeaks.main

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.ActivityMainBinding
import kaleidot725.highestpeaks.main.history.HistoryFragment
import kaleidot725.highestpeaks.main.home.HomeFragment
import kaleidot725.highestpeaks.main.settinglist.SettingListFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.camera.CameraActivity
import kaleidot725.highestpeaks.contact.ContactActivity
import kaleidot725.highestpeaks.main.history.HistoryFragmentMode
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.preview.PreviewActivity
import kaleidot725.highestpeaks.setting.SettingActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainNavigator, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mainMenuSelected : Holder<MainMenu>

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.main_actionbar)

        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions( this, permissions, 0)

        viewModel = ViewModelProviders.of(this, MainViewModelFactory(this)).get(MainViewModel::class.java)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel =  viewModel
        binding.lifecycleOwner = this

        restoreMenu()
    }


    val REQUEST_IMAGE_CAPTURE = 1
    var currentPhotoPath: String = ""

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply { currentPhotoPath = absolutePath }
    }

    override fun navigateCamera() : Boolean{
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile : File? = try { createImageFile() } catch(ex : IOException) { null }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", it)
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
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

    override fun navigateHome() : Boolean{
        supportFragmentManager.beginTransaction().replace(R.id.main_content, HomeFragment.newInstance()).commit()
        mainMenuSelected.value = MainMenu.Home
        return true
    }

    override fun navigateHistory(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.main_content, HistoryFragment.newInstance()).commit()
        mainMenuSelected.value = MainMenu.History
        return true
    }

    override fun navigateSettingList() : Boolean{
        supportFragmentManager.beginTransaction().replace(R.id.main_content, SettingListFragment.newInstance()).commit()
        mainMenuSelected.value = MainMenu.SettingList
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    private fun restoreMenu() : Boolean {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.menu.findItem(when(mainMenuSelected.value) {
            MainMenu.Home -> kaleidot725.highestpeaks.R.id.action_home
            MainMenu.History -> kaleidot725.highestpeaks.R.id.action_history
            MainMenu.SettingList -> kaleidot725.highestpeaks.R.id.action_setting
        }).setChecked(true)

        when(mainMenuSelected.value) {
            MainMenu.Home -> return navigateHome()
            MainMenu.History -> return navigateHistory()
            MainMenu.SettingList -> return navigateSettingList()
        }
    }
}
