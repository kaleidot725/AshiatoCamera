package kaleidot725.ashiato.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kaleidot725.ashiato.R

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SettingFragment.newInstance()).commitNow()
        }
    }
}
