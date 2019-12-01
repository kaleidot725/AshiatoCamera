package kaleidot725.ashiato.data.repository

import kaleidot725.ashiato.data.service.picture.Color

interface ColorRepository {
    fun all(): List<Color>
    fun count(): Int
}