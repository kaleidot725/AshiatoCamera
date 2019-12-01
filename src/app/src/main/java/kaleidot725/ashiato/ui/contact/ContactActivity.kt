package kaleidot725.ashiato.ui.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kaleidot725.ashiato.R

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContactFragment.newInstance()).commitNow()
        }
    }
}
