package kaleidot725.highestpeaks.di.repository

import kaleidot725.highestpeaks.di.data.Format
import kaleidot725.highestpeaks.di.data.Style

interface StyleRepository {
    fun all() : List<Style>
    fun count() : Int
}