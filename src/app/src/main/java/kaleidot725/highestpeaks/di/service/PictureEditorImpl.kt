package kaleidot725.highestpeaks.di.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import kaleidot725.highestpeaks.di.data.Picture

class PictureEditorImpl(val picture : Picture, val config : Bitmap.Config) : PictureEditor {
    private val options = BitmapFactory.Options().also { it.inMutable = true }
    override val bitmap : Bitmap = BitmapFactory.decodeFile(picture.path, options)
    override val canvas : Canvas = Canvas(bitmap).also { it.drawBitmap(bitmap, 0f, 0f, Paint()) }

    override fun drawText(text: String, color : Int, textSize : Float) {
        val paint = Paint()
        paint.color = color
        paint.textSize = textSize
        canvas.drawText(text, 0f, textSize, paint)
    }
}
