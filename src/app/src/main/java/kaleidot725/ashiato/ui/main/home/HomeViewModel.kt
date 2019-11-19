package kaleidot725.ashiato.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.di.repository.DateTimeRepository
import kaleidot725.ashiato.di.repository.LocationRepository
import kaleidot725.ashiato.di.repository.PictureRepository
import kaleidot725.ashiato.ui.main.MainNavigator
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(
    private val navigator: MainNavigator,
    private val dateTimeRepository: DateTimeRepository,
    private val locationRepository: LocationRepository,
    private val pictureRepository: PictureRepository
) :
    ViewModel() {
    private val _update: MutableLiveData<String> = MutableLiveData()
    val update: LiveData<String> get() = _update

    private val _altitude: MutableLiveData<String> = MutableLiveData()
    val altitude: LiveData<String> get() = _altitude

    private val _latitude: MutableLiveData<String> = MutableLiveData()
    val latitude: LiveData<String> get() = _latitude

    private val _longitude: MutableLiveData<String> = MutableLiveData()
    val longitude: LiveData<String> get() = _longitude

    private val _address: MutableLiveData<String> = MutableLiveData()
    val address: LiveData<String> get() = _address

    private val _weather: MutableLiveData<String> = MutableLiveData()
    val weather: LiveData<String> get() = _weather

    private val df: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd\nHH:mm:ss", Locale.getDefault())
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        val lastUpdate = df.format(dateTimeRepository.lastDate)
        _update.postValue(lastUpdate)

        val lastAltitude = "${locationRepository.lastAltitude.toInt()}m"
        _altitude.postValue(lastAltitude)

        val lastLatitude = "${locationRepository.lastLatitude.toInt()}°"
        _latitude.postValue(lastLatitude)

        val lastLongitude = "${locationRepository.lastLongitude.toInt()}°"
        _longitude.postValue(lastLongitude)

        val lastAddress = locationRepository.lastAddress
        _address.postValue(lastAddress)

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
