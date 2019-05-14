package kaleidot725.highestpeaks.setting

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import android.widget.AdapterView
import kaleidot725.highestpeaks.model.data.Provider
import kaleidot725.highestpeaks.model.repository.PersistenceSetting
import kaleidot725.highestpeaks.model.data.Setting
import java.lang.Exception

class SettingViewModel(persistenceSetting: PersistenceSetting) : ViewModel() {

    private val gpsGpsProviders : List<Provider> = arrayListOf(Provider.GPS, Provider.NETWORK)
    val gpsProviderMenus : List<String> = arrayListOf("GPS", "Network")

    private val gpsMinTimes : List<Int> = arrayListOf( 1, 5, 10, 15, 30, 60)
    val gpsMinTimeMenus : List<String> = arrayListOf("1Sec", "5Sec", "10Sec", "15Sec", "30Sec", "60Sec")

    private val gpsMinDistances : List<Int> = arrayListOf( 1, 5, 10, 25, 50, 100)
    val gpsMinDistanceMenus : List<String> = arrayListOf("1m", "5m", "10m", "25m", "50m", "100m")

    private val persistenceSetting : PersistenceSetting = persistenceSetting
    private var setting : Setting =
        Setting(Provider.GPS, gpsMinTimes[0], gpsMinDistances[0])

    init {
        try {
            setting = persistenceSetting.load()
        }
        catch (e : Exception) {
            Log.v("SettingViewModel", e.message)
        }
    }

    fun onGpsProviderMenuSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        setting.gpsGpsProvider = gpsGpsProviders[position]
        persistenceSetting.save(setting)
    }

    fun onGpsMinTimeMenuSelected(parent : AdapterView<*>, view: View, position: Int, id: Long) {
        setting.gpsMinTime = gpsMinTimes[position]
        persistenceSetting.save(setting)
    }

    fun onGpsMinDistanceMenuSelected(parent : AdapterView<*>, view : View, position: Int , id : Long) {
        setting.gpsMinDistance = gpsMinDistances[position]
        persistenceSetting.save(setting)
    }
}
