package kaleidot725.daycamera.data.repository

import kaleidot725.daycamera.data.service.picture.Angle

interface AngleRepository {
    fun all(): List<Angle>
    fun count(): Int
}