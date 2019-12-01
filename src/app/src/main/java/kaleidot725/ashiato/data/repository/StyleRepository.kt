package kaleidot725.ashiato.data.repository

import kaleidot725.ashiato.data.service.picture.Style

interface StyleRepository {
    fun all(): List<Style>
    fun count(): Int
}