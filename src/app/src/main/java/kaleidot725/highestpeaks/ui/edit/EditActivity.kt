package kaleidot725.highestpeaks.ui.edit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.R
import javax.inject.Inject

class EditActivity : AppCompatActivity(), HasSupportFragmentInjector, EditNavigator {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_edit)
        supportFragmentManager.beginTransaction().replace(R.id.display_content, EditFragment.newInstance()).commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun exit(): Boolean {
        finish()
        return true
    }
}
