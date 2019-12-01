package kaleidot725.ashiato.data.service.picture

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kaleidot725.ashiato.data.repository.AngleRepository

class RotationEditorImpl(
    private val angleRepository: AngleRepository
) : RotationEditor {

    private var _lastEnabled: Angle = angleRepository.all().first()
    override val lastEnabled: Angle get() = _lastEnabled

    private val _enabled: PublishSubject<Angle> = PublishSubject.create()
    override val enabled: Subject<Angle> get() = _enabled

    override fun enable(angle: Angle) {
        _lastEnabled = angle
        enabled.onNext(angle)
    }
}