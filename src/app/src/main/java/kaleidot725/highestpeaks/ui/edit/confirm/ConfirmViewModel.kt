package kaleidot725.highestpeaks.ui.edit.confirm

import android.graphics.*
import android.view.View
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.holder.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.di.service.saveAsJpegFile
import kaleidot725.highestpeaks.ui.edit.EditNavigator
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ConfirmViewModel(
    val navigator: EditNavigator,
    val locationRepository: LocationRepository,
    val editPicture : Holder<Picture>,
    val pictureEditor : PictureEditor
) : ViewModel() {

    val tempPath : String = editPicture.lastedValue.path + "_temp"
    val editText : String = "${SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Date())}" + " " + "${locationRepository.lastAltitude?.toInt()}m"

    init {
        pictureEditor.drawText(editText, Color.WHITE, 64f)
        pictureEditor.saveAsJpegFile(tempPath, 100)
    }

    fun save(view : View) {
        pictureEditor.saveAsJpegFile(editPicture.lastedValue.path, 100)
        File(tempPath).delete()
        navigator.exit()
    }

    fun cancel(view : View) {
        File(tempPath).delete()
        navigator.exit()
    }
}