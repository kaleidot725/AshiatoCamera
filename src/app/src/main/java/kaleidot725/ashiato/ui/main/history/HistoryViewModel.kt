package kaleidot725.ashiato.ui.main.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.di.service.picture.Picture
import kaleidot725.ashiato.di.repository.PictureRepository
import kaleidot725.ashiato.ui.main.MainNavigator
import java.io.File

class HistoryViewModel(
    private val navigator: MainNavigator,
    private val actor: HistoryFragmentActor,
    private val pictureRepository: PictureRepository
) : ViewModel() {
    private val _pictureViewModels: MutableLiveData<List<PictureViewModelBase>> = MutableLiveData()
    val pictureViewModels: LiveData<List<PictureViewModelBase>> get() = _pictureViewModels

    private val _notFound: MutableLiveData<Boolean> = MutableLiveData()
    val notFound: LiveData<Boolean> get() = _notFound

    fun load(mode: HistoryFragmentMode) {
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
            } catch (e: Exception) {
                Log.e("HistoryViewModel", e.toString())
            }

            Log.v("HistoryViewModel", it.path.value)
        }

        _notFound.value = (pictureRepository.count() == 0)
    }

    fun share() {
        val files = mutableListOf<File>()
        _pictureViewModels.value?.forEach {
            try {
                val shareable = it.isChecked.value ?: false
                if (shareable) {
                    files.add(File(it.path.value))
                }
            } catch (e: Exception) {
                Log.e("HistoryViewModel", e.toString())
            }

            Log.v("HistoryViewModel", it.path.value)
        }
        navigator.navigateShare(files)
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
        navigator: MainNavigator,
        actor: HistoryFragmentActor,
        pictureRepository: PictureRepository,
        picture: Picture,
        mode: HistoryFragmentMode
    ): PictureViewModelBase {

        when (mode) {
            HistoryFragmentMode.Action -> return PictureViewModelForAction(navigator, actor, pictureRepository, picture)
            HistoryFragmentMode.Display -> return PictureViewModelForDisplay(
                navigator,
                actor,
                pictureRepository,
                picture
            )
        }
    }
}
