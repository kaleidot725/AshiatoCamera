package kaleidot725.highestpeaks.main

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat

import android.widget.TextView
import androidx.core.app.ActivityCompat
import kaleidot725.highestpeaks.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UserにPermissionが必要な項目の了承を得る
        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions( this, permissions, 0)

        val transaction = supportFragmentManager.beginTransaction()
        val fragment = MainFragment.newInstance()
        transaction.replace(R.id.content, fragment)
        transaction.commit()
    }
}
