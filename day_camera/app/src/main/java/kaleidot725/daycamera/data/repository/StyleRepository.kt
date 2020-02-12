package kaleidot725.daycamera.data.repository

import kaleidot725.daycamera.data.service.picture.Style

interface StyleRepository {
    fun all(): List<Style>
    fun count(): Int
}