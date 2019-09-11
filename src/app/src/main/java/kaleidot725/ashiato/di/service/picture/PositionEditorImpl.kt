package kaleidot725.ashiato.di.service.picture

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kaleidot725.ashiato.di.repository.PositionRepository

class PositionEditorImpl(
    private val positionRepository: PositionRepository
) : PositionEditor {
    private var _lastEnabled: Position = positionRepository.all().first()
    override val lastEnabled: Position get() = _lastEnabled

    private val _enabled: PublishSubject<Position> = PublishSubject.create()
    override val enabled: Subject<Position> get() = _enabled

    override fun enable(position: Position) {
        _lastEnabled = position
        enabled.onNext(position)
    }
}