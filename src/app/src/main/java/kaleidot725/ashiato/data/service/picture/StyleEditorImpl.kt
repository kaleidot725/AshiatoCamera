package kaleidot725.ashiato.data.service.picture

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kaleidot725.ashiato.data.repository.StyleRepository

class StyleEditorImpl(
    private val styleRepository: StyleRepository
) : StyleEditor {
    private var _lastEnabled: Style = styleRepository.all().first()
    override val lastEnabled: Style get() = _lastEnabled

    private val _enabled: PublishSubject<Style> = PublishSubject.create()
    override val enabled: Subject<Style> get() = _enabled

    override val min: Float = styleRepository.all().minBy { it.dp }?.dp ?: 0f
    override val max: Float = styleRepository.all().maxBy { it.dp }?.dp ?: 0f

    override fun enable(style: Style) {
        _lastEnabled = style
        enabled.onNext(style)
    }
}