package kaleidot725.highestpeaks.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.MyApplicationNavigator
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository

class HistoryViewModel(
    private val navigator : MyApplicationNavigator,
    private val pictureRepository: PictureRepository,
    private val preview : Holder<Picture>)
    : ViewModel()
{
    private val _pictureViewModels : MutableLiveData<List<PictureViewModel>> = MutableLiveData()
    val pictureViewModels : LiveData<List<PictureViewModel>> get() = _pictureViewModels

    init {
        _pictureViewModels.value = createPictureViewModels()
    }

    fun load() {
        _pictureViewModels.postValue(createPictureViewModels())
    }

    private fun createPictureViewModels() : List<PictureViewModel> {
        val pictures = pictureRepository.all().reversed()
        val vms : MutableList<PictureViewModel> = mutableListOf()
        for (picture in pictures) {
            vms.add(PictureViewModel(navigator, picture, preview))
        }
        return vms
    }
}
