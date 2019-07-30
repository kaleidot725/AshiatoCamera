package kaleidot725.highestpeaks.di.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.lifecycle.LiveData
import io.reactivex.subjects.Subject
import kaleidot725.highestpeaks.di.data.Picture

interface PictureEditor  {
    val preview : Picture
    val target : Picture
    val state : Subject<PictureEditorState>

    fun start()
    fun modifyText(text : String)
    fun modifyColor(color : Int)
    fun modifyPosition(position : Int)
    fun modifyRotation(degree : Int)
    fun end()
    fun cancel()
}