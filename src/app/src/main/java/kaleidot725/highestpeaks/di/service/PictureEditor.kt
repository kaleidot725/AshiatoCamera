package kaleidot725.highestpeaks.di.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import io.reactivex.subjects.Subject
import kaleidot725.highestpeaks.di.data.Picture

interface PictureEditor  {
    fun start(path : String) : Subject<String>
    fun modifyText(text : String)
    fun modifyColor(color : Int)
    fun modifyPosition(position : Int)
    fun modifyRotation(degree : Int)
    fun end(path : String)
    fun cancel()
}