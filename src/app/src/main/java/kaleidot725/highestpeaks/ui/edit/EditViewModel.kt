package kaleidot725.highestpeaks.ui.edit

import android.graphics.*
import android.view.View
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.di.service.saveAsJpegFile
import java.util.*

class EditViewModel(
    val navigator: EditNavigator,
    val locationRepository: LocationRepository,
    val editPicture : Holder<Picture>,
    val pictureEditor : PictureEditor
) : ViewModel() {

    val editPath : String = editPicture.value.path
    val editText : String = "${Date().toString()}    ${locationRepository.lastAltitude?.toInt()}m"

    override fun onCleared() {
        super.onCleared()
    }

    fun save(view : View) {
        pictureEditor.drawText(editText, Color.WHITE, 32f)
        pictureEditor.saveAsJpegFile(editPicture.value.path, 100)
        navigator.exit()
    }

    fun cancel(view : View) {
        navigator.exit()
    }
}