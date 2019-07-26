package kaleidot725.highestpeaks.di.repository

import kaleidot725.highestpeaks.di.data.Format

interface FormatRepository {
    fun all() : List<Format>
    fun count() : Int
}