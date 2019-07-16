package kaleidot725.highestpeaks.di.service

import kaleidot725.highestpeaks.di.data.Picture
import android.R.attr.orientation
import android.graphics.*
import android.media.ExifInterface
import java.io.File
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth




class PictureEditorImpl(val picture : Picture, val config : Bitmap.Config) : PictureEditor {
    override val bitmap : Bitmap
    override val canvas : Canvas

    init {
        val rotationAngle = getRotationAngle(picture.path)
        val matrix = Matrix().also { it.postRotate(rotationAngle.toFloat()) }
        val options = BitmapFactory.Options().also { it.inMutable = true }
        val original = BitmapFactory.decodeFile(picture.path, options)

        bitmap = Bitmap.createBitmap(original, 0, 0, original.width, original.height, matrix, true)
        canvas = Canvas(bitmap).also { it.drawBitmap(bitmap, 0f, 0f, Paint()) }
    }

    override fun drawText(text: String, color : Int, textSize : Float) {
        val paint = Paint()
        paint.color = color
        paint.textSize = textSize
        canvas.drawText(text, 0f, textSize, paint)
    }

    private fun getRotationAngle(path : String) : Int {
        val exif = ExifInterface(picture.path)
        val orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION)
        val orientation = if (orientString != null) Integer.parseInt(orientString!!) else ExifInterface.ORIENTATION_NORMAL

        if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
            return 90

        if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
            return 180

        if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
            return 270

        return 0
    }
}
