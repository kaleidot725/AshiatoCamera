package kaleidot725.daycamera.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.daycamera.data.repository.DateTimeRepository
import kaleidot725.daycamera.data.repository.LocationRepository
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(
    private val dateTimeRepository: DateTimeRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {
    private val _update: MutableLiveData<String> = MutableLiveData()
    val update: LiveData<String> get() = _update

    private val _latLong: MutableLiveData<String> = MutableLiveData()
    val latLong: LiveData<String> get() = _latLong

    private val _elevation: MutableLiveData<String> = MutableLiveData()
    val elevation: LiveData<String> get() = _elevation

    private val _address: MutableLiveData<String> = MutableLiveData()
    val address: LiveData<String> get() = _address

    private val df: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        val lastUpdate = df.format(dateTimeRepository.lastDate)
        _update.postValue(lastUpdate)

        val latLongString = "${locationRepository.lastLatitude.toInt()}° ${locationRepository.lastLongitude.toInt()}°"
        _latLong.postValue(latLongString)

        val elevationString = "${locationRepository.lastAltitude.toInt()} M"
        _elevation.postValue(elevationString)

        val lastAddress = locationRepository.lastAddress
        _address.postValue(lastAddress)

        var disposable = dateTimeRepository.date.subscribe {
            _update.postValue(df.format(it))
        }
        compositeDisposable.add(disposable)

        disposable = locationRepository.altitude.subscribe {
            _elevation.postValue("${locationRepository.lastAltitude.toInt()} M")
        }
        compositeDisposable.add(disposable)

        disposable = locationRepository.latitude.subscribe {
            _latLong.postValue("\"${locationRepository.lastLatitude.toInt()}° \"${locationRepository.lastLongitude.toInt()}°")
        }
        compositeDisposable.add(disposable)

        disposable = locationRepository.longitude.subscribe {
            _latLong.postValue("\"${locationRepository.lastLatitude.toInt()}° \"${locationRepository.lastLongitude.toInt()}°")
        }
        compositeDisposable.add(disposable)

        disposable = locationRepository.address.subscribe {
            _address.postValue(it)
        }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}