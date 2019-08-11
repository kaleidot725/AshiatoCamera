package kaleidot725.highestpeaks.di.service

import io.reactivex.subjects.Subject
import kaleidot725.highestpeaks.di.data.Position

interface  PositionEditor {
    val lastEnabled : Position
    val enabled : Subject<Position>
    fun enable(position : Position)
}