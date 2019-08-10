package kaleidot725.highestpeaks.di.service

import io.reactivex.subjects.Subject
import kaleidot725.highestpeaks.di.data.Color

interface ColorEditor {
    val lastEnabled : Color
    val enabled : Subject<Color>
    fun enable(style : Color)
}