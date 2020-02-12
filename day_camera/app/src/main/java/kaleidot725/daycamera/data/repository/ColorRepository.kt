package kaleidot725.daycamera.data.repository

import kaleidot725.daycamera.data.service.picture.Color

interface ColorRepository {
    fun all(): List<Color>
    fun count(): Int
}