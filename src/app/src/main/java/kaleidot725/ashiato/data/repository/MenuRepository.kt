package kaleidot725.ashiato.data.repository

import kaleidot725.ashiato.data.service.contact.Menu

interface MenuRepository {
    fun all(): List<Menu>
    fun count(): Int
}