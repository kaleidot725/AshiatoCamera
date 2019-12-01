package kaleidot725.ashiato.data.repository

import kaleidot725.ashiato.data.service.picture.Position

interface PositionRepository {
    fun all(): List<Position>
    fun count(): Int
}