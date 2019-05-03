package kaleidot725.highestpeaks.license

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.R

class LicenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.license_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LicenseFragment.newInstance())
                .commitNow()
        }
    }

}
