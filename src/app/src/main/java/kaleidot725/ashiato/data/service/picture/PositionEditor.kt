package kaleidot725.ashiato.data.service.picture

import io.reactivex.subjects.Subject

interface PositionEditor {
    val lastEnabled: Position
    val enabled: Subject<Position>
    fun enable(position: Position)
}