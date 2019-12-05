package kaleidot725.ashiato.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.PictureRepository
import kaleidot725.ashiato.data.service.picture.Picture
import kotlinx.coroutines.launch
import java.io.File

class HistoryViewModel(
    private val pictureRepository: PictureRepository
) : ViewModel() {
    private val _pictureViewModels: MutableLiveData<List<PictureViewModelBase>> = MutableLiveData()
    val pictureViewModels: LiveData<List<PictureViewModelBase>> get() = _pictureViewModels

    private val _notFound: MutableLiveData<Boolean> = MutableLiveData()
    val notFound: LiveData<Boolean> get() = _notFound

    var navigator: HistoryFragmentNavigator? = null
    var actor: HistoryFragmentActor? = null

    fun load(mode: HistoryFragmentMode) {
        viewModelScope.launch {
            _pictureViewModels.value = createPictureViewModels(mode)
            _notFound.value = (pictureRepository.count() == 0)
        }
    }

    fun delete() {
        viewModelScope.launch {
            val items = _pictureViewModels.value?.filter { it.isChecked.value == true }
            val files = items?.map { File(it.path.value) } ?: listOf()
            files.forEach { it.delete() }
        }
        _notFound.value = (pictureRepository.count() == 0)
    }

    fun share() {
        viewModelScope.launch {
            val items = _pictureViewModels.value?.filter { it.isChecked.value == true }
            val files = items?.map { File(it.path.value) } ?: listOf()
            navigator?.navigateShare(files)
        }
    }

    private fun createPictureViewModels(mode: HistoryFragmentMode): List<PictureViewModelBase> {
        val pictures = pictureRepository.all()
        val vms: MutableList<PictureViewModelBase> = mutableListOf()
        for (picture in pictures) {
            vms.add(createPictureViewModel(navigator, actor, pictureRepository, picture, mode))
        }

        return vms
    }

    private fun createPictureViewModel(
        navigator: HistoryFragmentNavigator?,
        actor: HistoryFragmentActor?,
        pictureRepository: PictureRepository,
        picture: Picture,
        mode: HistoryFragmentMode
    ): PictureViewModelBase {

        when (mode) {
            HistoryFragmentMode.Action -> return PictureViewModelForAction(
                navigator,
                actor,
                pictureRepository,
                picture
            )
            HistoryFragmentMode.Display -> return PictureViewModelForDisplay(
                navigator,
                actor,
                pictureRepository,
                picture
            )
        }
    }
}
