package kaleidot725.highestpeaks

import android.location.LocationManager
import android.os.Environment
import dagger.*
import dagger.android.ContributesAndroidInjector
import kaleidot725.highestpeaks.camera.CameraActivity
import kaleidot725.highestpeaks.camera.CameraFragment
import kaleidot725.highestpeaks.contact.ContactActivity
import kaleidot725.highestpeaks.contact.ContactFragment
import kaleidot725.highestpeaks.main.MainActivity
import kaleidot725.highestpeaks.main.history.HistoryFragment
import kaleidot725.highestpeaks.main.home.HomeFragment
import kaleidot725.highestpeaks.main.settinglist.SettingListFragment
import kaleidot725.highestpeaks.model.service.LocationService
import kaleidot725.highestpeaks.setting.SettingActivity
import kaleidot725.highestpeaks.setting.SettingFragment
import kaleidot725.michetimer.model.repository.PictureRepositoryImpl
import kaleidot725.michetimer.model.repository.PictureRepository
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule
import kaleidot725.highestpeaks.main.MainMenu
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture
import kaleidot725.highestpeaks.model.data.Setting
import kaleidot725.highestpeaks.model.repository.*
import kaleidot725.highestpeaks.model.repository.PersistenceSetting
import kaleidot725.highestpeaks.preview.PreviewActivity
import kaleidot725.highestpeaks.preview.PreviewFragment
import java.lang.Exception


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideMyApplicationNavigator(myApplication : MyApplication) : MyApplicationNavigator {
        return myApplication
    }

    @Provides
    @Singleton
    fun proviceLocationService(myApplication : MyApplication) : LocationService {
        try {
            val setting = PersistenceSetting(myApplication.filesDir.path + "settings.json").load()
            return LocationService(myApplication).also { it.start(setting.gpsGpsLocationProvider, setting.gpsMinTime, setting.gpsMinDistance) }
        } catch (e : Exception) {
            val setting = Setting(LocationManager.GPS_PROVIDER, 1, 1)
            return LocationService(myApplication).also { it.start(setting.gpsGpsLocationProvider, setting.gpsMinTime, setting.gpsMinDistance) }
        }
    }

    @Provides
    @Singleton
    fun providePersistenceSettings(myApplication : MyApplication) : PersistenceSetting {
        return PersistenceSetting(myApplication.filesDir.path + "settings.json")
    }

    @Provides
    @Singleton
    fun providePictureRepository(myApplication : MyApplication): PictureRepository {
        val dcimPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val dirPath = "${dcimPath}/Highest-Peak"
        return PictureRepositoryImpl(dirPath)
    }

    @Provides
    @Singleton
    fun providePreviewPicture(myApplication: MyApplication) : Holder<Picture> {
        return Holder(Picture("", "", ""))
    }

    @Provides
    @Singleton
    fun provideMainMenuSelected(myApplication: MyApplication) : Holder<MainMenu> {
        return Holder(MainMenu.Home)
    }

    @Provides
    @Singleton
    fun provideDeveloperRepository(myApplication: MyApplication) : DeveloperRepository {
        return DeveloperRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideMenuRepository(myApplication : MyApplication) : MenuRepository {
        return MenuRepositoryImpl()
    }
}

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeCameraActivity(): CameraActivity

    @ContributesAndroidInjector
    abstract fun contributeSettingActivity(): SettingActivity

    @ContributesAndroidInjector
    abstract fun contributeContactActivity(): ContactActivity

    @ContributesAndroidInjector
    abstract fun contributePreviewActivity() : PreviewActivity
}

@Module
abstract class MainActivityModule {
    @Binds
    abstract fun bindsMainNavigator(activity: MainActivity): MainNavigator

    @ContributesAndroidInjector
    abstract fun contributeHistoryFragment(): HistoryFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingListFragment(): SettingListFragment
}

@Module
abstract class CameraActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeCameraFragment(): CameraFragment
}

@Module
abstract class ContactActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeContactFragment(): ContactFragment
}

@Module
abstract class SettingActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeSettingFragment(): SettingFragment
}

@Module
abstract class PreviewActivityModule {
    @ContributesAndroidInjector
    abstract fun contributePreviewFragment() : PreviewFragment
}

@Singleton
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    MainActivityModule::class,
    CameraActivityModule::class,
    ContactActivityModule::class,
    SettingActivityModule::class,
    PreviewActivityModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application:MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject (myApplication:MyApplication)
}