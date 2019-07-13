package kaleidot725.highestpeaks.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.highestpeaks.di.repository.LocationRepository
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(locationRepository: LocationRepository) : ViewModel() {
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

        val lastUpdate = if (locationRepository.lastUpdate == null) ("Updating") else (df.format(locationRepository.lastUpdate))
        _update.postValue(lastUpdate)

        val lastAltitude = if (locationRepository.lastAltitude == null) ("Unknown") else ("${locationRepository.lastAltitude?.toInt()}m")
        _altitude.postValue(lastAltitude)

        val lastLatitude = if (locationRepository.lastLatitude == null) ("Unknown") else ("${locationRepository.lastLatitude?.toInt()}°")
        _latitude.postValue(lastLatitude)

        val lastLongitude = if (locationRepository.lastLongitude == null) ("Unknown") else ("${locationRepository.lastLongitude?.toInt()}°")
        _longitude.postValue(lastLongitude)

        var disposable = locationRepository.update.subscribe {
            _update.postValue(df.format(it))
        }
        compositeDisposable.add(disposable)

        disposable = locationRepository.altitude.subscribe {
            _altitude.postValue("${it.toInt()}m")
        }
        compositeDisposable.add(disposable)

        disposable = locationRepository.latitude.subscribe {
            _latitude.postValue("${it.toInt()}°")
        }
        compositeDisposable.add(disposable)

        disposable = locationRepository.longitude.subscribe {
            _longitude.postValue("${it.toInt()}°")
        }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
