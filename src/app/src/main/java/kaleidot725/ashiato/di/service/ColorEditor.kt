package kaleidot725.ashiato.di.service

import io.reactivex.subjects.Subject
import kaleidot725.ashiato.di.data.Color

interface ColorEditor {
    val lastEnabled: Color
    val enabled: Subject<Color>
    fun enable(color: Color)
}