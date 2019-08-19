package kaleidot725.ashiato.di.repository

import kaleidot725.ashiato.di.data.Style

interface StyleRepository {
    fun all(): List<Style>
    fun count(): Int
}