package kaleidot725.ashiato.di.service.picture

import io.reactivex.subjects.Subject

interface StyleEditor {
    val lastEnabled: Style
    val enabled: Subject<Style>
    fun enable(style: Style)
}