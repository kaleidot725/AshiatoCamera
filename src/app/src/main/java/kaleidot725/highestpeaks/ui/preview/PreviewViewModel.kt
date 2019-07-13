package kaleidot725.highestpeaks.ui.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository

class PreviewViewModel(private val repository: PictureRepository, private val preview : Holder<Picture>) : ViewModel() {

    private val _name : MutableLiveData<String> = MutableLiveData()
    val name : LiveData<String> = _name

    private val _path : MutableLiveData<String> = MutableLiveData()
    val path : LiveData<String> get() = _path

    init {
        _path.value = preview.value.path
        _name.value = preview.value.name
    }
}
