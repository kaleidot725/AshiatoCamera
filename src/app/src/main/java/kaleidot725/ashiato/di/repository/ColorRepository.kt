package kaleidot725.ashiato.di.repository

import kaleidot725.ashiato.di.service.picture.Color

interface ColorRepository {
    fun all(): List<Color>
    fun count(): Int
}