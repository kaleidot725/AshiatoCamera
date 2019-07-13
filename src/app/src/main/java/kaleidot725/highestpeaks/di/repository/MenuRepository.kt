package kaleidot725.highestpeaks.di.repository

import kaleidot725.highestpeaks.di.data.Menu

interface MenuRepository {
    fun all() : List<Menu>
    fun count() : Int
}