package kaleidot725.highestpeaks.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.R
import javax.inject.Inject

class ContactActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_activity)

        AndroidInjection.inject(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, ContactFragment.newInstance()).commitNow()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
