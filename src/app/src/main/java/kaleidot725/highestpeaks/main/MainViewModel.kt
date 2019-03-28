package kaleidot725.highestpeaks.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kaleidot725.highestpeaks.service.LocationService

class MainViewModel(locationService: LocationService) : ViewModel(), Disposable {
    val altitude : MutableLiveData<String> = MutableLiveData()
    val latitude : MutableLiveData<String> = MutableLiveData()
    val longitude : MutableLiveData<String> = MutableLiveData()

    private val locationService : LocationService = locationService
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    init {
        altitude.postValue("Unknown")
        latitude.postValue("Unknown")
        longitude.postValue("Unknown")

        var disposable = locationService.altitude.subscribe {
            altitude.postValue("${it.toInt()}m")
        }
        compositeDisposable.add(disposable)

        disposable = locationService.latitude.subscribe {
            latitude.postValue("${it.toInt()}m")
        }
        compositeDisposable.add(disposable)

        disposable = locationService.longitude.subscribe {
            longitude.postValue("${it.toInt()}m")
        }
        compositeDisposable.add(disposable)
    }

    override fun dispose(){
        compositeDisposable.dispose()
    }

    override fun isDisposed(): Boolean = compositeDisposable.isDisposed()
}
