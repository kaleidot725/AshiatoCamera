package kaleidot725.highestpeaks.model.repository

import kaleidot725.highestpeaks.model.data.Menu

interface MenuRepository {
    fun all() : List<Menu>
    fun count() : Int
}