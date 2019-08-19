package kaleidot725.ashiato.di.repository

import kaleidot725.ashiato.di.data.Angle

interface AngleRepository {
    fun all(): List<Angle>
    fun count(): Int
}