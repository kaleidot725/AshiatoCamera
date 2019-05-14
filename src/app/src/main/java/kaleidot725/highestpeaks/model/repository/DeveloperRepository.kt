package kaleidot725.highestpeaks.model.repository

import kaleidot725.highestpeaks.model.data.Developer

interface DeveloperRepository {
    fun init()
    fun all() : List<Developer>
    fun count() : Int
}