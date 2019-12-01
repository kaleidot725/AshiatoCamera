package kaleidot725.ashiato.data.service.picture

import io.reactivex.subjects.Subject

interface RotationEditor {
    val lastEnabled: Angle
    val enabled: Subject<Angle>
    fun enable(angle: Angle)
}