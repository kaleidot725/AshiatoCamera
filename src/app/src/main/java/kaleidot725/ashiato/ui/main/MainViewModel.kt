package kaleidot725.ashiato.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.data.repository.EditType
import kaleidot725.ashiato.data.repository.PictureRepository

class MainViewModel(private val pictureRepository: PictureRepository) : ViewModel() {

    enum class NavEvent {
        Camera,
        Folder
    }

    private var disposed: Boolean = false

    private val _navigationEvent: MutableLiveData<NavEvent> = MutableLiveData()
    val navigationEvent: LiveData<NavEvent> = _navigationEvent

    fun takePhoto(view: View) {
        pictureRepository.edit(EditType.TOOK, pictureRepository.newPicture())
        _navigationEvent.postValue(NavEvent.Camera)
    }

    fun selectPhoto(view: View) {
        pictureRepository.edit(EditType.FOLDER, pictureRepository.newPicture())
        _navigationEvent.postValue(NavEvent.Folder)
    }
}