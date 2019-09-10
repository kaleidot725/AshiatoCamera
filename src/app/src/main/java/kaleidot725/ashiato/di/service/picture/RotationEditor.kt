package kaleidot725.ashiato.di.service.picture

import io.reactivex.subjects.Subject
import kaleidot725.ashiato.di.data.Angle

interface RotationEditor {
    val lastEnabled: Angle
    val enabled: Subject<Angle>
    fun enable(angle: Angle)
}