package kaleidot725.highestpeaks.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kaleidot725.highestpeaks.model.service.LocationService
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(locationService: LocationService) : ViewModel(), Disposable {
    val update : MutableLiveData<String> = MutableLiveData()
    val altitude : MutableLiveData<String> = MutableLiveData()
    val latitude : MutableLiveData<String> = MutableLiveData()
    val longitude : MutableLiveData<String> = MutableLiveData()

    private val df : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    init {
        update.postValue("Updating")
        altitude.postValue("Unknown")
        latitude.postValue("Unknown")
        longitude.postValue("Unknown")

        var disposable = locationService.update.subscribe {
            update.postValue(df.format(it))
        }
        compositeDisposable.add(disposable)

        disposable = locationService.altitude.subscribe {
            altitude.postValue("${it.toInt()}m")
        }
        compositeDisposable.add(disposable)

        disposable = locationService.latitude.subscribe {
            latitude.postValue("${it.toInt()}°")
        }
        compositeDisposable.add(disposable)

        disposable = locationService.longitude.subscribe {
            longitude.postValue("${it.toInt()}°")
        }
        compositeDisposable.add(disposable)
    }

    override fun dispose(){
        compositeDisposable.dispose()
    }

    override fun isDisposed(): Boolean = compositeDisposable.isDisposed()
}
