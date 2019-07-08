package kaleidot725.highestpeaks.edit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture
import javax.inject.Inject
import javax.inject.Named
import android.graphics.BitmapFactory


class EditCanvas(context : Context, attrs : AttributeSet) : View(context, attrs) {

    private var canvas : Canvas? = null

    @Override
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.canvas = canvas
    }

    fun drawBitmap(picture : Picture) {
        val bitmap = BitmapFactory.decodeFile(picture.path)
        this.canvas = Canvas()
        this.canvas?.drawBitmap(bitmap, 0f, 0f, Paint())
    }
}