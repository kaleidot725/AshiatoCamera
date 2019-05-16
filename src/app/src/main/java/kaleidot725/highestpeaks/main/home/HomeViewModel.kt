package kaleidot725.highestpeaks.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kaleidot725.highestpeaks.model.repository.PersistenceSetting
import kaleidot725.highestpeaks.model.service.LocationService
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(locationService: LocationService) : ViewModel() {
    private val _update : MutableLiveData<String> = MutableLiveData()
    val update : LiveData<String> get() = _update

    private val _altitude : MutableLiveData<String> = MutableLiveData()
    val altitude : LiveData<String> get() = _altitude

    private val _latitude : MutableLiveData<String> = MutableLiveData()
    val latitude : LiveData<String> get() = _latitude

    private val _longitude : MutableLiveData<String> = MutableLiveData()
    val longitude : LiveData<String> get() = _longitude

    private val df : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    init {

        val lastUpdate = if (locationService.lastUpdate == null) ("Updating") else (df.format(locationService.lastUpdate))
        _update.postValue(lastUpdate)

        val lastAltitude = if (locationService.lastAltitude == null) ("Unknown") else ("${locationService.lastAltitude?.toInt()}m")
        _altitude.postValue(lastAltitude)

        val lastLatitude = if (locationService.lastLatitude == null) ("Unknown") else ("${locationService.lastLatitude?.toInt()}°")
        _latitude.postValue(lastLatitude)

        val lastLongitude = if (locationService.lastLongitude == null) ("Unknown") else ("${locationService.lastLongitude?.toInt()}°")
        _longitude.postValue(lastLongitude)

        var disposable = locationService.update.subscribe {
            _update.postValue(df.format(it))
        }
        compositeDisposable.add(disposable)

        disposable = locationService.altitude.subscribe {
            _altitude.postValue("${it.toInt()}m")
        }
        compositeDisposable.add(disposable)

        disposable = locationService.latitude.subscribe {
            _latitude.postValue("${it.toInt()}°")
        }
        compositeDisposable.add(disposable)

        disposable = locationService.longitude.subscribe {
            _longitude.postValue("${it.toInt()}°")
        }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
