package kaleidot725.ashiato.ui.edit.confirm

import android.media.ExifInterface
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.data.repository.*
import kaleidot725.ashiato.data.service.picture.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ConfirmViewModel(
    private val pictureEditor: PictureEditor,
    private val formatEditor: FormatEditor,
    private val colorEditor: ColorEditor,
    private val styleEditor: StyleEditor,
    private val positionEditor: PositionEditor,
    private val rotationEditor: RotationEditor,
    private val pictureSetting: PermanentPictureSetting,
    private val dateTimeRepository: DateTimeRepository,
    private val locationRepository: LocationRepository,
    private val colorRepository: ColorRepository,
    private val formatRepository: FormatRepository,
    private val postionRepository: PositionRepository,
    private val styleRepository: StyleRepository,
    private val pictureRepository: PictureRepository,
    private val angleRepository: AngleRepository
) : ViewModel() {

    private val _editPath: MutableLiveData<String> = MutableLiveData()
    val editPath: LiveData<String> = _editPath

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun load() {
        if (pictureRepository.editPicture == null) {
            return
        }

        val disposable = pictureEditor.state.subscribe {
            if (it == PictureEditorState.Update) {
                if (pictureEditor.preview != null) {
                    _editPath.postValue(pictureEditor.preview!!.path)
                }
            }
        }
        compositeDisposable.add(disposable)

        val target = pictureRepository.editPicture as Picture
        val preview = pictureRepository.tmpPicture()

        when (pictureRepository.editType) {
            EditType.TOOK -> {
                setCurrentValueToEditor(target)
            }
            EditType.FOLDER -> {
                setPictureValueToEditor(target)
            }
        }

        viewModelScope.launch(Dispatchers.Default) {
            pictureEditor.start(target, preview)
            pictureEditor.modifyText(formatEditor.create())
            pictureEditor.modifyColor(colorEditor.lastEnabled.value)
            pictureEditor.modifyTextSize(styleEditor.lastEnabled.dp)
            pictureEditor.modifyRotation(rotationEditor.lastEnabled.value)
            pictureEditor.modifyPosition(positionEditor.lastEnabled.type)
            pictureEditor.commit()
        }
    }

    private fun setCurrentValueToEditor(target: Picture) {
        val setting = loadSetting()
        formatEditor.enableAll(false)
        for (format in setting.formats) {
            formatEditor.enable(format.type, true)
        }
        styleEditor.enable(setting.style)
        colorEditor.enable(setting.color)
        positionEditor.enable(setting.position)

        val angleValue = getRotationAngle(target.path)
        val angle = angleRepository.all().first { angle -> angle.value == angleValue }
        rotationEditor.enable(angle)

        formatEditor.set(
            dateTimeRepository.lastDate,
            locationRepository.lastAltitude,
            locationRepository.lastLatitude,
            locationRepository.lastLongitude,
            locationRepository.lastAddress
        )
    }

    private fun setPictureValueToEditor(target: Picture) {
        val setting = loadSetting()
        formatEditor.enableAll(false)
        for (format in setting.formats) {
            formatEditor.enable(format.type, true)
        }
        styleEditor.enable(setting.style)
        colorEditor.enable(setting.color)
        positionEditor.enable(setting.position)

        val exif = ExifInterface(target.path)
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

    private fun loadSetting(): PictureSetting {
        try {
            return pictureSetting.load()
        } catch (e: Exception) {
            return PictureSetting(
                colorRepository.all().first(),
                styleRepository.all().first(),
                listOf(formatRepository.all().first()),
                postionRepository.all().first(),
                angleRepository.all().first()
            )
        }
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