package kaleidot725.ashiato.ui

import android.Manifest
import android.app.Activity
import android.app.Application
import androidx.core.app.ActivityCompat
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kaleidot725.ashiato.di.DaggerAppComponent
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA)

        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}