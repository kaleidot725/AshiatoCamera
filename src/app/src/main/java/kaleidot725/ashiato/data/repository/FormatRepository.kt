package kaleidot725.ashiato.data.repository

import kaleidot725.ashiato.data.service.picture.Format

interface FormatRepository {
    fun all(): List<Format>
    fun count(): Int
}