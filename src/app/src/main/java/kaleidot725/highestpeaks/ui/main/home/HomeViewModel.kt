package kaleidot725.highestpeaks.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.highestpeaks.di.repository.DateTimeRepository
import kaleidot725.highestpeaks.di.repository.LocationRepository
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(dateTimeRepository : DateTimeRepository, locationRepository: LocationRepository) : ViewModel() {
    private val _update : MutableLiveData<String> = MutableLiveData()
    val update : LiveData<String> get() = _update

    private val _altitude : MutableLiveData<String> = MutableLiveData()
    val altitude : LiveData<String> get() = _altitude

    private val _latitude : MutableLiveData<String> = MutableLiveData()
    val latitude : LiveData<String> get() = _latitude

    private val _longitude : MutableLiveData<String> = MutableLiveData()
    val longitude : LiveData<String> get() = _longitude

    private val df : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd\nHH:mm:ss", Locale.getDefault())
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    init {

        val lastUpdate = if (dateTimeRepository.lastDate == null) ("Updating") else (df.format(dateTimeRepository.lastDate))
        _update.postValue(lastUpdate)

        val lastAltitude = if (locationRepository.lastAltitude == null) ("???m") else ("${locationRepository.lastAltitude?.toInt()}m")
        _altitude.postValue(lastAltitude)

        val lastLatitude = if (locationRepository.lastLatitude == null) ("???°") else ("${locationRepository.lastLatitude?.toInt()}°")
        _latitude.postValue(lastLatitude)

        val lastLongitude = if (locationRepository.lastLongitude == null) ("???°") else ("${locationRepository.lastLongitude?.toInt()}°")
        _longitude.postValue(lastLongitude)

        var disposable = dateTimeRepository.date.subscribe {
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
