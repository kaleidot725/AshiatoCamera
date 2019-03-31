package kaleidot725.highestpeaks.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import kaleidot725.highestpeaks.R


class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> {
                supportFragmentManager.beginTransaction().
                    replace(R.id.content, HomeFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_history -> {
                supportFragmentManager.beginTransaction().
                    replace(R.id.content, HistoryFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_setting -> {
                supportFragmentManager.beginTransaction().
                    replace(R.id.content, SettingFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions( this, permissions, 0)

        val transaction = supportFragmentManager.beginTransaction()
        val fragment = HomeFragment.newInstance()
        transaction.replace(R.id.content, fragment)
        transaction.commit()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
