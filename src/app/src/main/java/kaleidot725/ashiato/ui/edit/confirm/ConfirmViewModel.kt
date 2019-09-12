package kaleidot725.ashiato.ui.edit.confirm

import android.media.ExifInterface
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.di.service.picture.Picture
import kaleidot725.ashiato.di.repository.AngleRepository
import kaleidot725.ashiato.di.repository.DateTimeRepository
import kaleidot725.ashiato.di.repository.LocationRepository
import kaleidot725.ashiato.di.repository.PictureRepository
import kaleidot725.ashiato.di.service.picture.FormatEditor
import kaleidot725.ashiato.di.service.picture.PictureEditor
import kaleidot725.ashiato.di.service.picture.RotationEditor
import kaleidot725.ashiato.ui.edit.EditNavigator

class ConfirmViewModel(
    val navigator: EditNavigator,
    val dateTimeRepository: DateTimeRepository,
    val locationRepository: LocationRepository,
    val pictureRepository: PictureRepository,
    val angleRepository: AngleRepository,
    val formatEditor: FormatEditor,
    val rotationEditor: RotationEditor,
    val pictureEditor: PictureEditor
) : ViewModel() {

    private val _editPath: MutableLiveData<String> = MutableLiveData()
    val editPath: LiveData<String> = _editPath

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        if (pictureRepository.took == null) {
            navigator.exit()
        }

        formatEditor.setDate(dateTimeRepository.lastDate)
        formatEditor.setLocation(
            locationRepository.lastAltitude,
            locationRepository.lastLatitude,
            locationRepository.lastLongitude,
            locationRepository.lastAddress,
            locationRepository.lastWeather.weather.first().main
        )

        val target = pictureRepository.took as Picture
        val preview = pictureRepository.tmpPicture()

        val angleValue = getRotationAngle(target.path)
        val angle = angleRepository.all().filter { angle -> angle.value == angleValue }.first()
        rotationEditor.enable(angle)

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

    fun save(view: View) {
        pictureEditor.end()
        navigator.exit()
    }

    fun cancel(view: View) {
        pictureEditor.cancel()
        navigator.exit()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    private fun getRotationAngle(path : String) : Float {
        val exif = ExifInterface(path)
        val orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION)
        val orientation = if (orientString != null) Integer.parseInt(orientString) else ExifInterface.ORIENTATION_NORMAL

        if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
            return 90f

        if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
            return 180f

        if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
            return 270f

        return 0f
    }}