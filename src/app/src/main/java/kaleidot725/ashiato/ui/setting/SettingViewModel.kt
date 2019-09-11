package kaleidot725.ashiato.ui.setting

import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.di.service.location.Settings
import kaleidot725.ashiato.di.persistence.PersistenceSetting

class SettingViewModel(persistenceSetting: PersistenceSetting) : ViewModel() {

    private val gpsGpsLocationProviders: List<String> =
        arrayListOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER)
    val gpsProviderMenus: List<String> = arrayListOf("GPS", "Network")
    val gpsProviderItemPosition: MutableLiveData<Int> = MutableLiveData()

    private val gpsMinTimes: List<Int> = arrayListOf(1, 5, 10, 15, 30, 60)
    val gpsMinTimeMenus: List<String> = arrayListOf("1Sec", "5Sec", "10Sec", "15Sec", "30Sec", "60Sec")
    val gpsMinTimeItemPosition: MutableLiveData<Int> = MutableLiveData()

    private val gpsMinDistances: List<Int> = arrayListOf(1, 5, 10, 25, 50, 100)
    val gpsMinDistanceMenus: List<String> = arrayListOf("1m", "5m", "10m", "25m", "50m", "100m")
    val gpsMinDistancePosition: MutableLiveData<Int> = MutableLiveData()

    private val persistenceSetting: PersistenceSetting = persistenceSetting
    private var setting: Settings =
        Settings(
            LocationManager.GPS_PROVIDER,
            gpsMinTimes[0],
            gpsMinDistances[0]
        )

    init {
        try {
            setting = persistenceSetting.load()
        } catch (e: Exception) {
            Log.v("SettingViewModel", e.message)
        }

        gpsProviderItemPosition.postValue(gpsGpsLocationProviders.indexOf(setting.gpsGpsLocationProvider))
        gpsProviderItemPosition.observeForever {
            try {
                setting.gpsGpsLocationProvider = gpsGpsLocationProviders[it]
                persistenceSetting.save(setting)
            } catch (e: Exception) {
                Log.v("SettingViewModel", e.message)
            }
        }

        gpsMinTimeItemPosition.postValue(gpsMinTimes.indexOf(setting.gpsMinTime))
        gpsMinTimeItemPosition.observeForever {
            try {
                setting.gpsMinTime = gpsMinTimes[it]
                persistenceSetting.save(setting)
            } catch (e: Exception) {
                Log.v("SettingViewModel", e.message)
            }
        }

        gpsMinDistancePosition.postValue(gpsMinDistances.indexOf(setting.gpsMinDistance))
        gpsMinDistancePosition.observeForever {
            try {
                setting.gpsMinDistance = gpsMinDistances[it]
                persistenceSetting.save(setting)
            } catch (e: Exception) {
                Log.v("SettingViewModel", e.message)
            }
        }
    }
}
