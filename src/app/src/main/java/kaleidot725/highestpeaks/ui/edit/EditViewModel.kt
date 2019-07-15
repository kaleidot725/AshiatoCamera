package kaleidot725.highestpeaks.ui.edit

import android.graphics.*
import android.view.View
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.highestpeaks.ui.main.MainNavigator
import java.io.File
import java.io.FileOutputStream
import java.util.*

class EditViewModel(val navigator: EditNavigator, val locationRepository: LocationRepository, val editPicture : Holder<Picture>) : ViewModel() {

    val editPath : String = editPicture.value.path
    val editText : String = "${Date().toString()}    ${locationRepository.lastAltitude?.toInt()}m"

    override fun onCleared() {
        super.onCleared()
    }

    fun save(view : View) {
        val options = BitmapFactory.Options().also { it.inMutable = true }
        val bitmap = BitmapFactory.decodeFile(editPicture.value.path, options)

        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 32f

        val out = FileOutputStream(File(editPicture.value.path))
        val edit = Bitmap.createBitmap(bitmap!!.width, bitmap!!.height, Bitmap.Config.ARGB_8888)
        val scanvas = Canvas(edit)

        scanvas.drawBitmap(bitmap as Bitmap, 0f, 0f, paint)
        scanvas.drawText(editText, 0f, 32f, paint)
        edit!!.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.flush()
        out.close()

        navigator.exit()
    }
}