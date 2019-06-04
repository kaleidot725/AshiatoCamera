package kaleidot725.highestpeaks.preview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.R
import javax.inject.Inject

class PreviewActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PreviewFragment.newInstance())
                .commitNow()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}