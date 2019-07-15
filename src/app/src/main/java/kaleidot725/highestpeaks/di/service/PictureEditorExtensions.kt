package kaleidot725.highestpeaks.di.service

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

fun PictureEditor.saveAsJpegFile(path : String, quality : Int){
    val out = FileOutputStream(File(path))
    this.bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)
    out.flush()
    out.close()
}