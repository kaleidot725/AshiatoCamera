package kaleidot725.highestpeaks.edit

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import kaleidot725.highestpeaks.model.data.Picture
import android.util.Log
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.Contacts
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileInputStream

class EditCanvas(context : Context, attrs : AttributeSet) : View(context, attrs) {

    @Override
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    fun drawPicture(picture : Picture, width : Int, height : Int) {
        val file = File(picture.path)
        Log.v("EditCanvas", "exists ${file.exists()}")

        val options = BitmapFactory.Options()
        options.inMutable = true

        val bitmap = BitmapFactory.decodeFile(picture.path, options)
        if (bitmap != null) {
            val canvas  = Canvas()
            this.draw(canvas)
        }
        invalidate()
    }
}