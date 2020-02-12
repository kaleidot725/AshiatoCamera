package kaleidot725.daycamera.data.repository

import kaleidot725.daycamera.data.service.picture.Format

interface FormatRepository {
    fun all(): List<Format>
    fun count(): Int
}