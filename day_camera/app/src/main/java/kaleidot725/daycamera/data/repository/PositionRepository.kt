package kaleidot725.daycamera.data.repository

import kaleidot725.daycamera.data.service.picture.Position

interface PositionRepository {
    fun all(): List<Position>
    fun count(): Int
}