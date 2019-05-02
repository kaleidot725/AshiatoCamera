package kaleidot725.highestpeaks.camera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.R
import javax.inject.Inject

class CameraActivity : AppCompatActivity(), CameraNavigator, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        supportFragmentManager.beginTransaction().replace(R.id.camera_content, CameraFragment.newInstance()).commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
