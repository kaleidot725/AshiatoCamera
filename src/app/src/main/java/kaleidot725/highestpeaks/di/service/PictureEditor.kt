package kaleidot725.highestpeaks.di.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.lifecycle.LiveData
import io.reactivex.subjects.Subject
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.data.PositionType

interface PictureEditor  {
    var preview : Picture?
    var target : Picture?
    val state : Subject<PictureEditorState>

    fun start(target : Picture, preview : Picture)
    fun modifyText(text : String)
    fun modifyTextSize(size : Float)
    fun modifyColor(color : Int)
    fun modifyPosition(position : PositionType)
    fun modifyRotation(degree : Int)
    fun end()
    fun cancel()
}