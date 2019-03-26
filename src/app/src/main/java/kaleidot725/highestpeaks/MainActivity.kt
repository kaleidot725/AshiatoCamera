package kaleidot725.highestpeaks

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.core.content.ContextCompat

import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.widget.TextView
import androidx.core.app.ActivityCompat
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UserにPermissionが必要な項目の了承を得る
        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions( this, permissions, 0)

        // 緯度経度高度の更新開始
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val altitude = findViewById<TextView>(R.id.altitude_text)
        val latitude = findViewById<TextView>(R.id.latitude_text)
        val longitude = findViewById<TextView>(R.id.longitude_text)
        val locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                altitude.text = "${location.altitude.toInt()}m"
                latitude.text = "${location.latitude.toInt()}°"
                longitude.text = "${location.longitude.toInt()}°"
                Log.v("GPS", "高度:${location.altitude} 緯度:${location.latitude} 経度:${location.longitude}")
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

            }

            override fun onProviderEnabled(provider: String) {

            }

            override fun onProviderDisabled(provider: String) {

            }
        }

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED ) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
