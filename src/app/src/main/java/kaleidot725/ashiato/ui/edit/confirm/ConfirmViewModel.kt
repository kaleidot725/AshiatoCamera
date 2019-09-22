package kaleidot725.ashiato.ui.edit.confirm

import android.media.ExifInterface
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.di.repository.*
import kaleidot725.ashiato.di.service.picture.*
import kaleidot725.ashiato.ui.edit.EditNavigator
import java.lang.Exception

class ConfirmViewModel(
    private val navigator: EditNavigator,
    private val pictureEditor: PictureEditor,
    private val formatEditor: FormatEditor,
    private val colorEditor : ColorEditor,
    private val styleEditor: StyleEditor,
    private val positionEditor: PositionEditor,
    private val rotationEditor: RotationEditor,
    private val pictureSetting : PermanentPictureSetting,
    private val dateTimeRepository: DateTimeRepository,
    private val locationRepository: LocationRepository,
    private val formatRepository: FormatRepository,
    private val pictureRepository: PictureRepository,
    private val angleRepository: AngleRepository
) : ViewModel() {

    private val _editPath: MutableLiveData<String> = MutableLiveData()
    val editPath: LiveData<String> = _editPath

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        if (pictureRepository.took == null) {
            navigator.exit()
        }

        // get parameter
        val target = pictureRepository.took as Picture
        val preview = pictureRepository.tmpPicture()

        try {
            val setting = pictureSetting.load()
            formatEditor.enableAll(false)
            for(format in setting.formats) { formatEditor.enable(format.type, true) }
            styleEditor.enable(setting.style)
            colorEditor.enable(setting.color)
            positionEditor.enable(setting.position)
        } catch (e : Exception) {
            e.printStackTrace()
        }

        // rotation
        val angleValue = getRotationAngle(target.path)
        val angle = angleRepository.all().filter { angle -> angle.value == angleValue }.first()
        rotationEditor.enable(angle)

        // picture
        formatEditor.set(dateTimeRepository.lastDate, locationRepository.lastAltitude, locationRepository.lastLatitude,
            locationRepository.lastLongitude, locationRepository.lastAddress, locationRepository.lastWeather.weather.first().main)
        pictureEditor.start(target, preview)
        pictureEditor.modifyText(formatEditor.create())
        pictureEditor.modifyColor(colorEditor.lastEnabled.value)
        pictureEditor.modifyTextSize(styleEditor.lastEnabled.dp)
        pictureEditor.modifyRotation(rotationEditor.lastEnabled.value)
        pictureEditor.modifyPosition(positionEditor.lastEnabled.type)
        _editPath.value = pictureEditor.preview!!.path
        val disposable = pictureEditor.state.subscribe {
            if (pictureEditor.preview != null) {
                _editPath.postValue(pictureEditor.preview!!.path)
            }
        }
        compositeDisposable.add(disposable)
    }

    fun save(view: View) {
        val formats = formatRepository.all().filter { formatEditor.enabled(it.type) }
        val setting = PictureSetting(colorEditor.lastEnabled, styleEditor.lastEnabled, formats, positionEditor.lastEnabled, rotationEditor.lastEnabled)
        pictureSetting.save(setting)

        pictureEditor.end()
        navigator.exit()
    }

    fun cancel(view: View) {
        val formats = formatRepository.all().filter { formatEditor.enabled(it.type) }
        val setting = PictureSetting(colorEditor.lastEnabled, styleEditor.lastEnabled, formats, positionEditor.lastEnabled, rotationEditor.lastEnabled)
        pictureSetting.save(setting)

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
    }
}