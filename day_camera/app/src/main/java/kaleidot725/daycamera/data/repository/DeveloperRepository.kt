package kaleidot725.daycamera.data.repository

import kaleidot725.daycamera.data.service.contact.Developer

interface DeveloperRepository {
    fun all(): List<Developer>
    fun count(): Int
}