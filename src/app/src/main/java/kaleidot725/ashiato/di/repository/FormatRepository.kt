package kaleidot725.ashiato.di.repository

import kaleidot725.ashiato.di.data.Format

interface FormatRepository {
    fun all(): List<Format>
    fun count(): Int
}