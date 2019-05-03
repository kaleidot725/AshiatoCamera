package kaleidot725.highestpeaks

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kaleidot725.highestpeaks.camera.CameraActivity
import kaleidot725.highestpeaks.contact.ContactActivity
import kaleidot725.highestpeaks.setting.SettingActivity
import javax.inject.Inject

class MyApplication : Application(), MyApplicationNavigator, HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun navigateCamera() : Boolean{
        val intent = Intent(this, CameraActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        return true
    }

    override fun navigateSetting(): Boolean {
        val intent = Intent(this, SettingActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        return true
    }

    override fun navigateLicense(): Boolean {
        LibsBuilder()
            .withActivityTitle("License")
            .withShowLoadingProgress(false)
            .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR).start(this)
        return true
    }

    override fun navigateContact(): Boolean {
        val intent = Intent(this, ContactActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        return true
    }
}