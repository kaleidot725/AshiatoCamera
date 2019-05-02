package kaleidot725.highestpeaks.model.repository

interface MenuRepository {
    fun init()
    fun all() : List<Menu>
    fun count() : Int
}