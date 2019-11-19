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
import java.text.SimpleDateFormat
import java.util.*

class ConfirmViewModel(
    private val navigator: EditNavigator,
    private val pictureEditor: PictureEditor,
    private val formatEditor: FormatEditor,
    private val colorEditor: ColorEditor,
    private val styleEditor: StyleEditor,
    private val positionEditor: PositionEditor,
    private val rotationEditor: RotationEditor,
    private val pictureSetting: PermanentPictureSetting,
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
        if (pictureRepository.editPicture == null) {
            navigator.exit()
        }

        // get parameter
        val target = pictureRepository.editPicture as Picture
        val preview = pictureRepository.tmpPicture()

        loadSetting()

        // rotation
        rotateAutomatically(target.path)

        when (pictureRepository.editType) {
            EditType.TOOK -> {
                setCurrentValueToEditor()
            }
            EditType.FOLDER -> {
                setPictureValueToEditor(target.path)
            }
        }

        // picture
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

    private fun loadSetting() {
        try {
            val setting = pictureSetting.load()
            formatEditor.enableAll(false)
            for (format in setting.formats) {
                formatEditor.enable(format.type, true)
            }
            styleEditor.enable(setting.style)
            colorEditor.enable(setting.color)
            positionEditor.enable(setting.position)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun rotateAutomatically(path: String) {
        val angleValue = getRotationAngle(path)
        val angle = angleRepository.all().filter { angle -> angle.value == angleValue }.first()
        rotationEditor.enable(angle)
    }

    private fun setCurrentValueToEditor() {
        formatEditor.set(
            dateTimeRepository.lastDate,
            locationRepository.lastAltitude,
            locationRepository.lastLatitude,
            locationRepository.lastLongitude,
            locationRepository.lastAddress
        )
    }

    private fun setPictureValueToEditor(path: String) {
        val exif = ExifInterface(path)

        val simpleDateFormat = SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.getDefault())
        val dateString =
            exif.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL) ?: "1900:01:01 00:00:00"
        val lastDate = simpleDateFormat.parse(dateString) ?: Date()

        val lastAltitudeStr = exif.getAttribute(ExifInterface.TAG_GPS_ALTITUDE)
        val lastAltitude = lastAltitudeStr?.split("/")?.get(1) ?: "0"

        val lastLatitudeStr = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)
        val lastLatitude = lastLatitudeStr?.split("/")?.get(0) ?: "0"

        val lastLongitudeStr = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE)
        val lastLongitude = lastLongitudeStr?.split("/")?.get(0) ?: "0"

        val lastAddress =
            locationRepository.getAddress(lastLatitude.toDouble(), lastLongitude.toDouble())
        formatEditor.set(
            lastDate,
            lastAltitude.toDouble(),
            lastLatitude.toDouble(),
            lastLongitude.toDouble(),
            lastAddress
        )
    }

    fun save(view: View) {
        val formats = formatRepository.all().filter { formatEditor.enabled(it.type) }
        val setting = PictureSetting(
            colorEditor.lastEnabled,
            styleEditor.lastEnabled,
            formats,
            positionEditor.lastEnabled,
            rotationEditor.lastEnabled
        )
        pictureSetting.save(setting)

        pictureEditor.end()
        navigator.exit()
    }

    fun cancel(view: View) {
        val formats = formatRepository.all().filter { formatEditor.enabled(it.type) }
        val setting = PictureSetting(
            colorEditor.lastEnabled,
            styleEditor.lastEnabled,
            formats,
            positionEditor.lastEnabled,
            rotationEditor.lastEnabled
        )
        pictureSetting.save(setting)

        pictureEditor.cancel()
        navigator.exit()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    private fun getRotationAngle(path: String): Float {
        val exif = ExifInterface(path)
        val orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION)
        val orientation =
            if (orientString != null) Integer.parseInt(orientString) else ExifInterface.ORIENTATION_NORMAL

        if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
            return 90f

        if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
            return 180f

        if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
            return 270f

        return 0f
    }
}