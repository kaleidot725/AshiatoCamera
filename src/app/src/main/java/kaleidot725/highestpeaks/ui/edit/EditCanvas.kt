package kaleidot725.highestpeaks.ui.edit

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kaleidot725.highestpeaks.di.data.Picture
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import android.graphics.Bitmap



class EditCanvas(context : Context, attrs : AttributeSet) : View(context, attrs) {
    var picture : Picture? = null
    var bitmap : Bitmap? = null
    var text : String? = null
    var btop : Float = 0f

    @Override
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (bitmap != null && text != null && picture != null) {

            val paint = Paint()
            paint.color = Color.WHITE
            paint.textSize = 64f
            canvas?.drawBitmap(bitmap!!, 0f, btop, paint)
            canvas?.drawText(text!!, 0f, btop + 64f, paint)

            val out = FileOutputStream(File(picture!!.path + "__"))
            val edit = Bitmap.createBitmap(bitmap!!.width, bitmap!!.height, Bitmap.Config.ARGB_8888)
            val scanvas = Canvas(edit)
            scanvas.drawBitmap(bitmap as Bitmap, 0f, 0f, paint)
            scanvas.drawText(text!!, 0f, 64f, paint)
            edit!!.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
        }
    }

    fun drawPicture(picture : Picture, text : String, frameWidth : Int, frameHeight : Int) {
        val options = BitmapFactory.Options().also { it.inMutable = true }
        this.bitmap = BitmapFactory.decodeFile(picture.path, options)

        val matrix = Matrix().also { it.postRotate(90f) }
        this.bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap!!.width, bitmap!!.height, matrix, true)

        val ratioBitmap = bitmap!!.width.toFloat() / bitmap!!.height.toFloat()
        val ratioFrame = frameWidth.toFloat() / frameHeight.toFloat()

        var finalWidth = frameWidth.toInt()
        var finalHeight = frameHeight.toInt()

        if (ratioFrame > 1) {
            finalWidth = (frameWidth.toFloat() * ratioBitmap).toInt()
        } else {
            finalHeight = (frameHeight.toFloat() * ratioBitmap).toInt()
        }

        Log.v("TAG", "frameWidth ${frameWidth} frameHeight ${frameHeight} bitmapWidth ${bitmap!!.width} bitmapHeight ${bitmap!!.height} finalWidth ${finalWidth} finalHeight ${finalHeight}")

        this.picture = picture
        this.bitmap = Bitmap.createScaledBitmap(bitmap, finalWidth, finalHeight, true)
        this.text = text
        this.btop = ((frameHeight - finalHeight) / 2).toFloat() - ((frameHeight - finalHeight) / 4)
        invalidate()
    }
}