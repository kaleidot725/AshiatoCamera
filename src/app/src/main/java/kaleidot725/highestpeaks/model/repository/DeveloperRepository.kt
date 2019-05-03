package kaleidot725.highestpeaks.model.repository

interface DeveloperRepository {
    fun init()
    fun all() : List<Developer>
    fun count() : Int
}