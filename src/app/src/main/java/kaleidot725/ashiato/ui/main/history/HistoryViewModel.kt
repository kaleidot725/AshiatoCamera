package kaleidot725.ashiato.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.PictureRepository
import kaleidot725.ashiato.data.service.picture.Picture
import kotlinx.coroutines.launch
import java.io.File

class HistoryViewModel(private val pictureRepository: PictureRepository) : ViewModel() {
    private val _pictureViewModels: MutableLiveData<List<PictureViewModelBase>> = MutableLiveData()
    val pictureViewModels: LiveData<List<PictureViewModelBase>> get() = _pictureViewModels

    private val _notFound: MutableLiveData<Boolean> = MutableLiveData()
    val notFound: LiveData<Boolean> get() = _notFound

    private val _shareItems: MutableLiveData<List<Picture>> = MutableLiveData()
    val shareItems: LiveData<List<Picture>> = _shareItems

    private val _selectedItem: MutableLiveData<Picture> = MutableLiveData()
    val selectedItem: LiveData<Picture> = _selectedItem

    private val _event: MutableLiveData<NavEvent> = MutableLiveData()
    val event: LiveData<NavEvent> = _event

    fun load(mode: HistoryFragmentMode) {
        viewModelScope.launch {
            _pictureViewModels.value = createPictureViewModels(mode)
            _notFound.value = (pictureRepository.count() == 0)
        }
    }

    fun delete() {
        viewModelScope.launch {
            val files = getSelectedPictures().map { File(it.path) }
            files.forEach { it.delete() }
            _notFound.postValue(pictureRepository.count() == 0)
        }
    }

    fun share() {
        viewModelScope.launch {
            _shareItems.postValue(getSelectedPictures())
            _event.postValue(NavEvent.SHARE)
        }
    }

    private fun getSelectedPictures(): List<Picture> {
        return _pictureViewModels.value?.filter {
            (it.isChecked.value == true) && (it.picture.value != null)
        }?.map {
            requireNotNull(it.picture.value)
        } ?: listOf()
    }

    private fun createPictureViewModels(mode: HistoryFragmentMode): List<PictureViewModelBase> {
        return pictureRepository.all().map { createPictureViewModel(it, mode) }
    }

    private fun createPictureViewModel(picture: Picture, mode: HistoryFragmentMode): PictureViewModelBase {
        when (mode) {
            HistoryFragmentMode.Action -> {
                return PictureViewModelForAction(
                    pictureRepository,
                    picture,
                    (picture == _selectedItem.value)
                )
            }
            HistoryFragmentMode.Display -> return PictureViewModelForDisplay(
                pictureRepository,
                picture,
                {
                    pictureRepository.preview(picture)
                    _event.postValue(NavEvent.PREVIEW)
                },
                {
                    _selectedItem.postValue(picture)
                    _event.postValue(NavEvent.ACTION)
                }
            )
        }
    }

    enum class NavEvent {
        SHARE,
        PREVIEW,
        ACTION
    }
}
