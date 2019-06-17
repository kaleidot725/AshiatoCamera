package kaleidot725.highestpeaks.main

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.ActivityMainBinding
import kaleidot725.highestpeaks.main.history.HistoryFragment
import kaleidot725.highestpeaks.main.home.HomeFragment
import kaleidot725.highestpeaks.main.settinglist.SettingListFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.model.data.Holder
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

        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions( this, permissions, 0)

        viewModel = ViewModelProviders.of(this, MainViewModelFactory(this)).get(MainViewModel::class.java)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel =  viewModel
        binding.lifecycleOwner = this

        restoreMenu()
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
}
