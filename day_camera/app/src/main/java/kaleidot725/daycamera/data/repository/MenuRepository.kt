package kaleidot725.daycamera.data.repository

import kaleidot725.daycamera.data.service.contact.Menu

interface MenuRepository {
    fun all(): List<Menu>
    fun count(): Int
}