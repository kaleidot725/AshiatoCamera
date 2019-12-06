package kaleidot725.ashiato.ui.main.history

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.data.repository.PictureRepository
import kaleidot725.ashiato.data.service.picture.Picture

abstract class PictureViewModelBase(
    private val pictureRepository: PictureRepository,
    private val p: Picture
) : ViewModel() {
    internal val _name: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = p.name
    }
    val name: LiveData<String> = _name

    internal val _path: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = p.path
    }
    val path: LiveData<String> get() = _path

    internal val _picture: MutableLiveData<Picture> = MutableLiveData<Picture>().apply {
        value = p
    }
    val picture: LiveData<Picture> = _picture

    internal val _isChecked: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isChecked: LiveData<Boolean> = _isChecked

    abstract fun click(view: View)
    abstract fun longClick(view: View): Boolean
}





