package kaleidot725.ashiato.ui.edit.confirm

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.di.data.Picture
import kaleidot725.ashiato.di.repository.DateTimeRepository
import kaleidot725.ashiato.di.repository.LocationRepository
import kaleidot725.ashiato.di.repository.PictureRepository
import kaleidot725.ashiato.di.service.FormatEditor
import kaleidot725.ashiato.di.service.PictureEditor
import kaleidot725.ashiato.di.service.RotationEditor
import kaleidot725.ashiato.ui.edit.EditNavigator

class ConfirmViewModel(
    val navigator: EditNavigator,
    val dateTimeRepository: DateTimeRepository,
    val locationRepository: LocationRepository,
    val pictureRepository: PictureRepository,
    val formatEditor : FormatEditor,
    val rotationEditor: RotationEditor,
    val pictureEditor : PictureEditor
) : ViewModel() {

    private val _editPath : MutableLiveData<String> = MutableLiveData()
    val editPath : LiveData<String> = _editPath

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    init {
        if (pictureRepository.took == null) {
            navigator.exit()
        }

        formatEditor.setDate(dateTimeRepository.lastDate)
        formatEditor.setLocation(locationRepository.lastAltitude, locationRepository.lastLatitude, locationRepository.lastLongitude)
        val target = pictureRepository.took as Picture
        val preview = pictureRepository.tmpPicture()
        pictureEditor.start(target, preview)
        pictureEditor.modifyText(formatEditor.create())
        pictureEditor.modifyRotation(rotationEditor.lastEnabled.value)

        _editPath.value = pictureEditor.preview!!.path
        val disposable = pictureEditor.state.subscribe {
            if (pictureEditor.preview != null) {
                _editPath.postValue(pictureEditor.preview!!.path)
            }
        }
        compositeDisposable.add(disposable)
    }

    fun save(view : View) {
        pictureEditor.end()
        navigator.exit()
    }

    fun cancel(view : View) {
        pictureEditor.cancel()
        navigator.exit()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}