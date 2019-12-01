package kaleidot725.ashiato.ui.main.history

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.data.repository.PictureRepository
import kaleidot725.ashiato.data.service.picture.Picture

abstract class PictureViewModelBase(
    private val navigation: HistoryFragmentNavigator?,
    private val actor: HistoryFragmentActor?,
    private val pictureRepository: PictureRepository,
    private val picture: Picture
) : ViewModel() {

    internal val _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> = _name

    internal val _path: MutableLiveData<String> = MutableLiveData()
    val path: LiveData<String> get() = _path

    internal val _isChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isChecked: LiveData<Boolean> = _isChecked

    init {
        _path.value = picture.path
        _name.value = picture.name
        _isChecked.value = false
    }

    abstract fun click(view: View)
    abstract fun longClick(view: View): Boolean
}





