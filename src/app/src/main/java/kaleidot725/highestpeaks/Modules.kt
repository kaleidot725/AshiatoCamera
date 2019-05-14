package kaleidot725.highestpeaks

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
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.repository.*
import kaleidot725.highestpeaks.model.repository.PersistenceSetting


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
        return LocationService(myApplication).also { it.start() }
    }

    @Provides
    @Singleton
    fun providePersistenceSettings(myApplication : MyApplication) : PersistenceSetting {
        return PersistenceSetting("settings.json")
    }

    @Provides
    @Singleton
    fun providePictureRepository(myApplication : MyApplication): PictureRepository {
        val dcimPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val dirPath = "${dcimPath}/Highest-Peak"
        return PictureRepositoryImpl(dirPath).also { it.init() }
    }

    @Provides
    @Singleton
    fun provideDeveloperRepository(myApplication: MyApplication) : DeveloperRepository {
        return DeveloperRepositoryImpl().also { it.init() }
    }

    @Provides
    @Singleton
    fun provideMenuRepository(myApplication : MyApplication) : MenuRepository {
        return MenuRepositoryImpl().also { it.init() }
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

@Singleton
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    MainActivityModule::class,
    CameraActivityModule::class,
    ContactActivityModule::class,
    SettingActivityModule::class
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