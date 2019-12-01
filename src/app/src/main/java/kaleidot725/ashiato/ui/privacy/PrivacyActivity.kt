package kaleidot725.ashiato.ui.privacy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kaleidot725.ashiato.R

class PrivacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.privacy_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PrivacyFragment.newInstance())
                .commitNow()
        }
    }
}
