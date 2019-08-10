package kaleidot725.highestpeaks.di.repository

import kaleidot725.highestpeaks.di.data.Color

interface ColorRepository {
    fun all() : List<Color>
    fun count() : Int
}