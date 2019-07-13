package kaleidot725.highestpeaks.di.repository

import kaleidot725.highestpeaks.di.data.Developer

interface DeveloperRepository {
    fun all() : List<Developer>
    fun count() : Int
}