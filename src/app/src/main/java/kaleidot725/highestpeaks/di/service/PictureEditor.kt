package kaleidot725.highestpeaks.di.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas

interface PictureEditor {
    val bitmap  : Bitmap
    val canvas  : Canvas

    fun drawText(text: String, color : Int, textSize : Float)
}