package kaleidot725.highestpeaks.main

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.ActivityMainBinding
import kaleidot725.highestpeaks.main.history.HistoryFragment
import kaleidot725.highestpeaks.main.home.HomeFragment
import kaleidot725.highestpeaks.main.setting.SettingFragment
import java.lang.Exception
import android.content.Intent
import kaleidot725.highestpeaks.main.camera.CameraActivity


class MainActivity : AppCompatActivity(), MainNavigator {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions( this, permissions, 0)

        viewModel = ViewModelProviders.of(this, MainViewModelFactory()).get(MainViewModel::class.java)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel =  viewModel
        binding.lifecycleOwner = this

        navigateHome()
    }

    override fun navigateCamera() : Boolean{
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
        return true
    }

    override fun navigateHome() : Boolean{
        supportFragmentManager.beginTransaction().replace(R.id.main_content, HomeFragment.newInstance()).commit()
        return true
    }

    override fun navigateHistory(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.main_content, HistoryFragment.newInstance()).commit()
        return true
    }

    override fun navigateSetting() : Boolean{
        supportFragmentManager.beginTransaction().replace(R.id.main_content, SettingFragment.newInstance()).commit()
        return true
    }

    private inner class MainViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass == MainViewModel::class.java) {
                return MainViewModel(this@MainActivity) as  T
            }

            throw Exception("have created unknown class type")
        }
    }
}
