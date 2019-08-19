package kaleidot725.ashiato.di.repository

import kaleidot725.ashiato.di.data.Developer

interface DeveloperRepository {
    fun all(): List<Developer>
    fun count(): Int
}