package kaleidot725.ashiato.di.repository

import kaleidot725.ashiato.di.service.contact.Menu

interface MenuRepository {
    fun all(): List<Menu>
    fun count(): Int
}