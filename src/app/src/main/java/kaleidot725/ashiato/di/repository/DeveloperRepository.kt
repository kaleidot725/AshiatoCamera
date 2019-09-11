package kaleidot725.ashiato.di.repository

import kaleidot725.ashiato.di.service.contact.Developer

interface DeveloperRepository {
    fun all(): List<Developer>
    fun count(): Int
}