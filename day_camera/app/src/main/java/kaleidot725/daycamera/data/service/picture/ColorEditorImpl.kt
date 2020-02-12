package kaleidot725.daycamera.data.service.picture

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kaleidot725.daycamera.data.repository.ColorRepository

class ColorEditorImpl(
    private val colorRepository: ColorRepository
) : ColorEditor {
    private var _lastEnabled: Color = colorRepository.all().first()
    override val lastEnabled: Color get() = _lastEnabled

    private val _enabled: PublishSubject<Color> = PublishSubject.create()
    override val enabled: Subject<Color> get() = _enabled

    override fun enable(color: Color) {
        _lastEnabled = color
        enabled.onNext(color)
    }
}