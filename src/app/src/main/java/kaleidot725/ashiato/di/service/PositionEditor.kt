package kaleidot725.ashiato.di.service

import io.reactivex.subjects.Subject
import kaleidot725.ashiato.di.data.Position

interface  PositionEditor {
    val lastEnabled : Position
    val enabled : Subject<Position>
    fun enable(position : Position)
}