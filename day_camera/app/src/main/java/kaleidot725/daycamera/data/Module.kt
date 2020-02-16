package kaleidot725.ashiato.data

import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import android.os.Environment
import kaleidot725.daycamera.data.repository.*
import kaleidot725.daycamera.data.service.location.LocationSetting
import kaleidot725.daycamera.data.service.location.PermanentLocationSetting
import kaleidot725.daycamera.data.service.picture.*
import org.koin.android.ext.koin.androidContext
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

}
