package kaleidot725.ashiato.ui.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kaleidot725.ashiato.R
import kaleidot725.ashiato.data.holder.Holder
import kaleidot725.ashiato.data.repository.PictureRepository
import kaleidot725.ashiato.databinding.ActivityMainBinding
import kaleidot725.ashiato.ui.edit.EditActivity
import kaleidot725.ashiato.ui.main.history.HistoryFragment
import kaleidot725.ashiato.ui.main.home.HomeFragment
import kaleidot725.ashiato.ui.main.settinglist.SettingListFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity(), MainNavigator, EasyPermissions.PermissionCallbacks {
    companion object {
        private const val REQUEST_PERMISSION = 0
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_GET_CONTENT = 2
        private var REQUEST_RETRY: Boolean = false
        private val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
    }

    val pictureRepository: PictureRepository by inject()
    val mainMenuSelected: Holder<MainMenu> by inject()
    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!EasyPermissions.hasPermissions(this, *permissions)) {
            EasyPermissions.requestPermissions(
                this,
                this.resources.getString(R.string.permissoin_message),
                REQUEST_PERMISSION,
                *permissions
            )

            REQUEST_RETRY = true
            return
        }

        setupCalligraphy()
        setContentView(R.layout.activity_main)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.actionbar_main)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigationEvent.observe(this, Observer {
            when (it) {
                MainViewModel.NavEvent.Camera -> navigateCamera()
                MainViewModel.NavEvent.Folder -> navigateFolder()
                MainViewModel.NavEvent.Home -> navigateHome()
                MainViewModel.NavEvent.History -> navigateHistory()
                MainViewModel.NavEvent.SettingList -> navigateSettingList()
            }
        })

        val cameraButton = findViewById<ImageButton>(R.id.camera_button)
        cameraButton.setOnClickListener {
            viewModel.takePhoto(cameraButton)
        }

        val folderButton = findViewById<ImageButton>(R.id.folder_button)
        folderButton.setOnClickListener {
            viewModel.selectPhoto(folderButton)
        }

        restoreMenu()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    private fun setupCalligraphy() {
        val config = CalligraphyConfig.Builder().build()
        val interceptor = CalligraphyInterceptor(config)
        val pump = ViewPump.builder().addInterceptor(interceptor).build()
        ViewPump.init(pump)
    }

    private var tempFile: File? = null
    private var imageFile: File? = null

    private fun getFilePath(context: Context, uri: Uri): String {
        var cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor.moveToFirst()
        var imageId = cursor.getString(0)
        imageId = imageId.substring(imageId.lastIndexOf(":") + 1);
        cursor.close()

        cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            MediaStore.Images.Media._ID + " = ? ",
            arrayOf(imageId),
            null
        )
        cursor.moveToFirst()
        val str = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor.close()
        return str
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                if (resultCode == Activity.RESULT_OK) {
                    if (tempFile != null && imageFile != null) {
                        tempFile?.copyTo(imageFile as File)
                        navigateEdit()
                    }
                }
            }
            REQUEST_GET_CONTENT -> {
                if (resultCode == Activity.RESULT_OK) {
                    val uri = data?.data
                    if (uri != null) {
                        val path = getFilePath(this, uri)
                        val tempFile = File(path)
                        tempFile.copyTo(imageFile as File)
                        navigateEdit()
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun navigateFolder(): Boolean {
        Intent(Intent.ACTION_GET_CONTENT).also { getContentIntent ->
            getContentIntent.addCategory(Intent.CATEGORY_OPENABLE)
            getContentIntent.type = "image/*"

            val intent = Intent.createChooser(getContentIntent, "Select Picture")
            this.imageFile = File(pictureRepository.editPicture!!.path)
            startActivityForResult(intent, REQUEST_GET_CONTENT)
        }

        return true
    }

    @Throws(IOException::class)
    override fun navigateCamera(): Boolean {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.putExtra("android.intent.extra.quickCapture", true)
            takePictureIntent.resolveActivity(packageManager)?.also {
                val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                tempFile = File("${storageDir}/temp.jpg")

                val photoURI: Uri =
                    FileProvider.getUriForFile(
                        this,
                        applicationContext.packageName + ".provider",
                        tempFile as File
                    )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

                this.imageFile = File(pictureRepository.editPicture!!.path)
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

    override fun navigateSettingList(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, SettingListFragment.newInstance()).commit()
        mainMenuSelected.update(MainMenu.SettingList)
        return true
    }

    override fun navigateHome(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, HomeFragment.newInstance()).commit()
        mainMenuSelected.update(MainMenu.Home)
        return true
    }

    override fun navigateHistory(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, HistoryFragment.newInstance()).commit()
        mainMenuSelected.update(MainMenu.History)
        return true
    }

    private fun restoreMenu(): Boolean {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val item = bottomNavigation.menu.findItem(
            when (mainMenuSelected.lastedValue) {
                MainMenu.Home -> R.id.action_home
                MainMenu.History -> R.id.action_history
                MainMenu.SettingList -> R.id.action_setting
            }
        )
        item.setChecked(true)

        when (mainMenuSelected.lastedValue) {
            MainMenu.Home -> return navigateHome()
            MainMenu.History -> return navigateHistory()
            MainMenu.SettingList -> return navigateSettingList()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, list: List<String>) {
        recreate()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        finishAffinity()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val result = EasyPermissions.hasPermissions(this, *permissions)
        if (!result && REQUEST_RETRY) {
            finishAffinity()
        } else {
            recreate()
        }
    }
}
