package kaleidot725.ashiato.di.service.picture

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kaleidot725.ashiato.di.data.Style
import kaleidot725.ashiato.di.repository.StyleRepository

class StyleEditorImpl(
    private val styleRepository: StyleRepository
) : StyleEditor {
    private var _lastEnabled: Style = styleRepository.all().first()
    override val lastEnabled: Style get() = _lastEnabled

    private val _enabled: PublishSubject<Style> = PublishSubject.create()
    override val enabled: Subject<Style> get() = _enabled

    override fun enable(style: Style) {
        _lastEnabled = style
        enabled.onNext(style)
    }
}