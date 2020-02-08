package kaleidot725.ashiato.data

import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import android.os.Environment
import kaleidot725.ashiato.data.holder.Holder
import kaleidot725.ashiato.data.holder.HolderImpl
import kaleidot725.ashiato.data.repository.*
import kaleidot725.ashiato.data.service.location.LocationSetting
import kaleidot725.ashiato.data.service.location.PermanentLocationSetting
import kaleidot725.ashiato.data.service.picture.*
import kaleidot725.ashiato.ui.contact.ContactViewModel
import kaleidot725.ashiato.ui.edit.EditViewModel
import kaleidot725.ashiato.ui.edit.color.ColorViewModel
import kaleidot725.ashiato.ui.edit.confirm.ConfirmViewModel
import kaleidot725.ashiato.ui.edit.format.FormatViewModel
import kaleidot725.ashiato.ui.edit.position.PositionViewModel
import kaleidot725.ashiato.ui.edit.rotation.RotationViewModel
import kaleidot725.ashiato.ui.edit.style.StyleViewModel
import kaleidot725.ashiato.ui.main.MainMenu
import kaleidot725.ashiato.ui.main.history.HistoryViewModel
import kaleidot725.ashiato.ui.main.home.HomeViewModel
import kaleidot725.ashiato.ui.main.settinglist.SettingListViewModel
import kaleidot725.ashiato.ui.preview.PageViewModel
import kaleidot725.ashiato.ui.preview.PreviewViewModel
import kaleidot725.ashiato.ui.setting.SettingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

val appModule = module {
    single {
        PermanentLocationSetting(androidContext().filesDir.path + "location.json")
    }

    single {
        PermanentPictureSetting(androidContext().filesDir.path + "picture.json")
    }

    single<PictureRepository> {
        val dcimPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val dirPath = "${dcimPath}/Highest-Peak"
        PictureRepositoryImpl(dirPath)
    }

    single<LocationRepository> {
        val geocoder: Geocoder = Geocoder(androidContext(), Locale.getDefault())
        val locationManager: LocationManager =
            androidContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        try {
            val s = PermanentLocationSetting(androidContext().filesDir.path + "settings.json")
                .load()
            LocationRepositoryImpl(
                androidContext(),
                s.gpsGpsLocationProvider,
                s.gpsMinTime,
                s.gpsMinDistance,
                geocoder,
                locationManager
            ).apply {
                start()
            }

        } catch (e: Exception) {
            val s = LocationSetting(LocationManager.GPS_PROVIDER, 1, 1)
            LocationRepositoryImpl(
                androidContext(),
                s.gpsGpsLocationProvider,
                s.gpsMinTime,
                s.gpsMinDistance,
                geocoder,
                locationManager
            ).apply {
                start()
            }
        }
    }

    single<DeveloperRepository> {
        DeveloperRepositoryImpl()
    }

    single<StyleRepository> {
        StyleRepositoryImpl()
    }

    single<ColorRepository> {
        ColorRepositoryImpl()
    }

    single<DateTimeRepository> {
        DateTimeRepositoryImpl().apply { start(1000) }
    }

    single<PositionRepository> {
        PositionRepositoryImpl()
    }

    single<MenuRepository> {
        MenuRepositoryImpl(androidContext())
    }

    single<AngleRepository> {
        AngleRepositoryImpl()
    }

    single<Holder<MainMenu>> {
        HolderImpl<MainMenu>(MainMenu.Home)
    }

    single<FormatEditor> {
        FormatEditorImpl()
    }

    single<ColorEditor> {
        ColorEditorImpl(get())
    }

    single<StyleEditor> {
        StyleEditorImpl(get())
    }

    single<PositionEditor> {
        PositionEditorImpl(get())
    }

    single<RotationEditor> {
        RotationEditorImpl(get())
    }

    single<PictureEditor> {
        PictureEditorImpl(DrawableCanvasImpl())
    }

    single<FormatRepository> {
        FormatRepositoryImpl()
    }

    viewModel {
        ContactViewModel(get())
    }

    viewModel {
        ColorViewModel(get(), get(), get())
    }

    viewModel {
        ConfirmViewModel(
            get(), get(), get(), get(), get(), get(),
            get(), get(), get(), get(), get(), get(), get(), get(), get()
        )
    }

    viewModel {
        FormatViewModel(get(), get(), get())
    }

    viewModel {
        PositionViewModel(get(), get(), get())
    }

    viewModel {
        HomeViewModel(get(), get(), get())
    }

    viewModel {
        RotationViewModel(get(), get(), get())
    }

    viewModel {
        StyleViewModel(get(), get(), get())
    }

    viewModel {
        EditViewModel(
            get(), get(), get(), get(), get(), get(),
            get(), get(), get(), get(), get(), get()
        )
    }

    viewModel {
        HistoryViewModel(get())
    }

    viewModel {
        SettingListViewModel(get(), get())
    }

    viewModel {
        PageViewModel(get())
    }

    viewModel {
        PreviewViewModel(get())
    }

    viewModel {
        SettingViewModel(get())
    }
}
