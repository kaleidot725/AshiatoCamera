package kaleidot725.ashiato.di.service.picture

import io.reactivex.subjects.Subject

interface PositionEditor {
    val lastEnabled: Position
    val enabled: Subject<Position>
    fun enable(position: Position)
}