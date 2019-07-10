package kaleidot725.highestpeaks.edit

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kaleidot725.highestpeaks.model.data.Picture
import android.util.Log
import android.os.Build
import android.provider.Contacts
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileInputStream

class EditCanvas(context : Context, attrs : AttributeSet) : View(context, attrs) {

    var bitmap : Bitmap? = null
    var text : String? = null
    var btop : Float = 0f

    @Override
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (bitmap != null && text != null) {
            val paint = Paint()
            paint.color = Color.RED
            paint.textSize = 64f
            canvas?.drawBitmap(bitmap!!, 0f, btop, paint)
            canvas?.drawText(text!!, 0f, btop + 64f, paint)
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

        this.bitmap = Bitmap.createScaledBitmap(bitmap, finalWidth, finalHeight, true)
        this.text = text
        this.btop = ((frameHeight - finalHeight) / 2).toFloat() - ((frameHeight - finalHeight) / 4)
        invalidate()
    }
}