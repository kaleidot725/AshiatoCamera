package kaleidot725.highestpeaks.di

import android.location.LocationManager
import android.os.Environment
import dagger.*
import dagger.android.ContributesAndroidInjector
import kaleidot725.highestpeaks.ui.edit.EditActivity
import kaleidot725.highestpeaks.ui.edit.confirm.ConfirmFragment
import kaleidot725.highestpeaks.ui.contact.ContactActivity
import kaleidot725.highestpeaks.ui.contact.ContactFragment
import kaleidot725.highestpeaks.ui.main.MainActivity
import kaleidot725.highestpeaks.ui.main.history.HistoryFragment
import kaleidot725.highestpeaks.ui.main.home.HomeFragment
import kaleidot725.highestpeaks.ui.main.settinglist.SettingListFragment
import kaleidot725.highestpeaks.di.repository.LocationRepositoryImpl
import kaleidot725.highestpeaks.ui.setting.SettingActivity
import kaleidot725.highestpeaks.ui.setting.SettingFragment
import kaleidot725.michetimer.model.repository.PictureRepositoryImpl
import kaleidot725.michetimer.model.repository.PictureRepository
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule
import kaleidot725.highestpeaks.ui.MyApplication
import kaleidot725.highestpeaks.ui.main.MainMenu
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.di.holder.Holder
import kaleidot725.highestpeaks.di.holder.HolderImpl
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.data.Settings
import kaleidot725.highestpeaks.di.repository.*
import kaleidot725.highestpeaks.di.persistence.PersistenceSetting
import kaleidot725.highestpeaks.di.service.*
import kaleidot725.highestpeaks.ui.edit.EditNavigator
import kaleidot725.highestpeaks.ui.edit.color.ColorFragment
import kaleidot725.highestpeaks.ui.edit.format.FormatFragment
import kaleidot725.highestpeaks.ui.edit.position.PositionFragment
import kaleidot725.highestpeaks.ui.edit.rotation.RotationFragment
import kaleidot725.highestpeaks.ui.preview.PreviewActivity
import kaleidot725.highestpeaks.ui.preview.PageFragment
import java.lang.Exception
import javax.inject.Named


@Module
class AppModule {
    // Setting
    @Provides
    @Singleton
    fun providePersistenceSettings(myApplication : MyApplication) : PersistenceSetting {
        return PersistenceSetting(myApplication.filesDir.path + "settings.json")
    }

    // Repository
    @Provides
    @Singleton
    fun providePictureRepository(myApplication : MyApplication): PictureRepository {
        val dcimPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val dirPath = "${dcimPath}/Highest-Peak"
        return PictureRepositoryImpl(dirPath)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(myApplication : MyApplication) : LocationRepository {
        try {
            val setting = PersistenceSetting(myApplication.filesDir.path + "settings.json").load()
            return LocationRepositoryImpl(myApplication)
                .also { it.start(setting.gpsGpsLocationProvider, setting.gpsMinTime, setting.gpsMinDistance) }
        } catch (e : Exception) {
            val setting = Settings(LocationManager.GPS_PROVIDER, 1, 1)
            return LocationRepositoryImpl(myApplication)
                .also { it.start(setting.gpsGpsLocationProvider, setting.gpsMinTime, setting.gpsMinDistance) }
        }
    }

    @Provides
    @Singleton
    fun provideDeveloperRepository(myApplication: MyApplication) : DeveloperRepository {
        return DeveloperRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideFormatRepository(myApplication : MyApplication) : FormatRepository {
        return FormatRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideDateTimeRepository(myApplication: MyApplication) : DateTimeRepository {
        val repository = DateTimeRepositoryImpl()
        repository.start(1000)
        return repository
    }

    @Provides
    @Singleton
    fun provideMenuRepository(myApplication : MyApplication) : MenuRepository {
        return MenuRepositoryImpl(myApplication)
    }

    @Provides
    @Singleton
    fun provideMainMenuSelected(myApplication: MyApplication) : Holder<MainMenu> {
        return HolderImpl<MainMenu>(MainMenu.Home)
    }

    // Editor
    @Provides
    @Singleton
    fun provideFormatEditor(myApplication: MyApplication) : FormatEditor {
        return FormatEditorImpl(provideDateTimeRepository(myApplication), provideLocationRepository(myApplication))
    }

    @Provides
    @Singleton
    fun providePictureEditor(myApplication: MyApplication) : PictureEditor {
        val drawableCanvas = DrawableCanvasImpl()
        return PictureEditorImpl(drawableCanvas)
    }
}

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [EditActivityModule::class])
    abstract fun contributeCameraActivity(): EditActivity

    @ContributesAndroidInjector(modules = [SettingActivityModule::class])
    abstract fun contributeSettingActivity(): SettingActivity

    @ContributesAndroidInjector(modules = [ContactActivityModule::class])
    abstract fun contributeContactActivity(): ContactActivity

    @ContributesAndroidInjector(modules = [PreviewActivityModule::class])
    abstract fun contributePreviewActivity() : PreviewActivity
}

@Module
abstract class MainActivityModule {
    @Binds
    abstract fun bindsMainNavigator(activity: MainActivity): MainNavigator

    @ContributesAndroidInjector(modules = [HistoryFragmentModule::class])
    abstract fun contributeHistoryFragment(): HistoryFragment

    @ContributesAndroidInjector(modules = [HomeFramentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [SettingListFramgnetModule::class])
    abstract fun contributeSettingListFragment(): SettingListFragment
}

@Module
abstract class HistoryFragmentModule {

}

@Module
abstract  class HomeFramentModule {

}

@Module
abstract  class SettingListFramgnetModule {

}

@Module
abstract class EditActivityModule {
    @Binds
    abstract fun bindsEditNavigator(activity: EditActivity): EditNavigator

    @ContributesAndroidInjector(modules = [ColorFragmentModule::class])
    abstract fun contributeColorFragment(): ColorFragment

    @ContributesAndroidInjector(modules = [ConfirmFragmentModule::class])
    abstract fun contributeConfirmFragment(): ConfirmFragment

    @ContributesAndroidInjector(modules = [FormatFragmentModule::class])
    abstract fun contributeFormatFragment(): FormatFragment

    @ContributesAndroidInjector(modules = [PositionFragmentModule::class])
    abstract fun contributePositionFragment(): PositionFragment

    @ContributesAndroidInjector(modules = [RotationFragmentModule::class])
    abstract fun contributeRotationFragment(): RotationFragment
}

@Module
abstract class ColorFragmentModule {

}

@Module
abstract class ConfirmFragmentModule {

}

@Module
abstract class FormatFragmentModule {

}

@Module
abstract class PositionFragmentModule {

}

@Module
abstract class RotationFragmentModule {

}

@Module
abstract class ContactActivityModule {
    @ContributesAndroidInjector(modules = [ContactFragmentModule::class])
    abstract fun contributeContactFragment(): ContactFragment
}

@Module
abstract class ContactFragmentModule {

}

@Module
abstract class SettingActivityModule {
    @ContributesAndroidInjector(modules = [SettingFragmentModule::class])
    abstract fun contributeSettingFragment(): SettingFragment
}

@Module
abstract class SettingFragmentModule {

}

@Module
abstract class PreviewActivityModule {
    @ContributesAndroidInjector(modules = [PreviewFragmentModule::class])
    abstract fun contributePreviewFragment() : PageFragment
}

@Module
abstract class PreviewFragmentModule {

}

@Singleton
@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class, ActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject (myApplication: MyApplication)
}
