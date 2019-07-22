package kaleidot725.highestpeaks.ui.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import kaleidot725.highestpeaks.di.repository.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository

class PreviewViewModel(repository: PictureRepository, picture : Picture) : ViewModel() {

    private val _name : MutableLiveData<String> = MutableLiveData()
    val name : LiveData<String> = _name

    private val _path : MutableLiveData<String> = MutableLiveData()
    val path : LiveData<String> get() = _path

    init {
        _path.value = picture.path
        _name.value = picture.name
    }

    override fun onCleared() {
        super.onCleared()
    }
}
