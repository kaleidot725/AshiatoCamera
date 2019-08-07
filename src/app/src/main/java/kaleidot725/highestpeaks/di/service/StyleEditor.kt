package kaleidot725.highestpeaks.di.service

import io.reactivex.subjects.Subject
import kaleidot725.highestpeaks.di.data.Style

interface StyleEditor {
    val lastEnabled : Style
    val enabled : Subject<Style>
    fun enable(style : Style)
}