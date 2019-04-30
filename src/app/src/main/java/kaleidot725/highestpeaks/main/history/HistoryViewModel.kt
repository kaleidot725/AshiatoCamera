package kaleidot725.highestpeaks.main.history

import android.graphics.Picture
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.michetimer.model.repository.PictureRepository

class HistoryViewModel(pictureRepository: PictureRepository) : ViewModel() {
    private val pictureRepository : PictureRepository = pictureRepository

    private val _pictureViewModels : MutableLiveData<List<PictureViewModel>> = MutableLiveData()
    val pictureViewModels : LiveData<List<PictureViewModel>> get() = _pictureViewModels

    init {
        val pictures = pictureRepository.all()
        val vms : MutableList<PictureViewModel> = mutableListOf()
        for (picture in pictures) {
            vms.add(PictureViewModel(picture))
        }

        _pictureViewModels.value = vms
    }
}
