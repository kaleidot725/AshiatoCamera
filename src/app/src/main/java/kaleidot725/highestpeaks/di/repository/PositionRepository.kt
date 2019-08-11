package kaleidot725.highestpeaks.di.repository

import kaleidot725.highestpeaks.di.data.Position

interface PositionRepository {
    fun all() : List<Position>
    fun count() : Int
}