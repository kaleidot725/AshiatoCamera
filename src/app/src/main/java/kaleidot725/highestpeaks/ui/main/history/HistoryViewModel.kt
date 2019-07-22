package kaleidot725.highestpeaks.ui.main.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.di.repository.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository
import java.io.File
import java.lang.Exception

class HistoryViewModel(
    private val navigator : MainNavigator,
    private val actor : HistoryFragmentActor,
    private val pictureRepository: PictureRepository,
    private val selected : Holder<Picture>
)
    : ViewModel()
{
    private val _pictureViewModels : MutableLiveData<List<PictureViewModelBase>> = MutableLiveData()
    val pictureViewModels : LiveData<List<PictureViewModelBase>> get() = _pictureViewModels

    private val _notFound : MutableLiveData<Boolean> = MutableLiveData()
    val notFound : LiveData<Boolean> get() = _notFound

    init {
        _pictureViewModels.value = createPictureViewModels(HistoryFragmentMode.Display)
        _notFound.value = (pictureRepository.count() == 0)
    }

    fun load(mode : HistoryFragmentMode) {
        _pictureViewModels.value = createPictureViewModels(mode)
        _notFound.value = (pictureRepository.count() == 0)
    }

    fun delete() {
        _pictureViewModels.value?.forEach {
            try {
                val deletable = it.isChecked.value ?: false
                if (deletable) {
                    File(it.path.value).delete()
                }
            } catch (e : Exception) {
                Log.e("HistoryViewModel", e.toString())
            }

            Log.v("HistoryViewModel", it.path.value)
        }

        _notFound.value = (pictureRepository.count() == 0)
    }

    private fun createPictureViewModels(mode : HistoryFragmentMode) : List<PictureViewModelBase> {
        val pictures = pictureRepository.all()
        val vms : MutableList<PictureViewModelBase> = mutableListOf()
        for (picture in pictures) {
            vms.add(createPictureViewModel(navigator, actor, picture, selected, mode))
        }

        return vms
    }

    private fun createPictureViewModel(
        navigator: MainNavigator,
        actor : HistoryFragmentActor,
        picture : Picture,
        preview : Holder<Picture>,
        mode : HistoryFragmentMode) : PictureViewModelBase {

        when(mode) {
            HistoryFragmentMode.Action -> return PictureViewModelForAction(navigator, actor, picture, preview)
            HistoryFragmentMode.Display -> return PictureViewModelForDisplay(navigator, actor, picture, preview)
        }
    }
}
