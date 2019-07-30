package kaleidot725.highestpeaks.di.service

import android.graphics.*
import android.media.ExifInterface
import java.io.File
import java.io.FileOutputStream

class DrawableCanvasImpl() : DrawableCanvas {
    private var currentPath : String = ""
    private lateinit var bitmap : Bitmap
    private lateinit var canvas : Canvas

    override fun load(path: String) {
        val degree = getRotationAngle(path)
        val matrix = Matrix().also {
            it.postRotate(degree)
        }

        val options = BitmapFactory.Options().also {
            it.inMutable = true
        }

        bitmap = BitmapFactory.decodeFile(path, options)
        canvas = Canvas(bitmap).also { it.drawBitmap(bitmap, 0f, 0f, Paint()) }
        currentPath = path
    }

    override fun draw(x : Float, y : Float, text: String, color : Int, size : Float) {
        val paint = Paint().also {
            it.color = color
            it.textSize = size
        }
        canvas.drawText(text, x, y, paint)
    }

    override fun rotation(degree: Float) {
        val matrix = Matrix().also { it.postRotate(degree) }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        canvas = Canvas(bitmap).also { it.drawBitmap(bitmap, 0f, 0f, Paint()) }
    }

    override fun write(path: String) {
        val out = FileOutputStream(File(path))
        this.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        out.flush()
        out.close()
    }

    override fun delete(path : String) {
        File(path).delete()
    }

    private fun getRotationAngle(path : String) : Float {
        val exif = ExifInterface(path)
        val orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION)
        val orientation = if (orientString != null) Integer.parseInt(orientString!!) else ExifInterface.ORIENTATION_NORMAL

        if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
            return 90f

        if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
            return 180f

        if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
            return 270f

        return 0f
    }
}