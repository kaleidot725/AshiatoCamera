package kaleidot725.ashiato.data.repository

import kaleidot725.ashiato.data.service.picture.Angle

interface AngleRepository {
    fun all(): List<Angle>
    fun count(): Int
}