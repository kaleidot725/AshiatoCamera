package kaleidot725.ashiato.data.service.picture

import io.reactivex.subjects.Subject

interface StyleEditor {
    val lastEnabled: Style
    val enabled: Subject<Style>
    fun enable(style: Style)

    val min: Float
    val max: Float
}