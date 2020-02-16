package kaleidot725.daycamera.data.repository

import kaleidot725.daycamera.data.service.contact.Menu

interface MenuRepository {
    suspend fun all(): List<Menu>
    suspend fun count(): Int
}