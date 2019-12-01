package kaleidot725.ashiato.data.service.picture

import io.reactivex.subjects.Subject

interface ColorEditor {
    val lastEnabled: Color
    val enabled: Subject<Color>
    fun enable(color: Color)
}