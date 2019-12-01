package kaleidot725.ashiato.data.repository

import kaleidot725.ashiato.data.service.contact.Developer

interface DeveloperRepository {
    fun all(): List<Developer>
    fun count(): Int
}