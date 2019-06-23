package kaleidot725.highestpeaks.main.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository
import java.io.File
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path

class HistoryViewModel(
    private val navigator : MainNavigator,
    private val actor : HistoryFragmentActor,
    private val pictureRepository: PictureRepository,
    private val selected : Holder<Picture>)
    : ViewModel()
{
    private val _pictureViewModels : MutableLiveData<List<PictureViewModelBase>> = MutableLiveData()
    val pictureViewModels : LiveData<List<PictureViewModelBase>> get() = _pictureViewModels

    init {
        _pictureViewModels.value = createPictureViewModels(HistoryFragmentMode.Display)
    }

    fun load(mode : HistoryFragmentMode) {
        _pictureViewModels.value = createPictureViewModels(mode)
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
    }

    private fun createPictureViewModels(mode : HistoryFragmentMode) : List<PictureViewModelBase> {
        val pictures = pictureRepository.all().reversed()
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
