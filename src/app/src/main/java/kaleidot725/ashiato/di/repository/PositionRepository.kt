package kaleidot725.ashiato.di.repository

import kaleidot725.ashiato.di.data.Position

interface PositionRepository {
    fun all() : List<Position>
    fun count() : Int
}