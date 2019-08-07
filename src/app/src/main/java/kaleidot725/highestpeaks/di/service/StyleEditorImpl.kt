package kaleidot725.highestpeaks.di.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kaleidot725.highestpeaks.di.data.Style
import kaleidot725.highestpeaks.di.repository.StyleRepository

class StyleEditorImpl(
    private val styleRepository : StyleRepository)
    : StyleEditor
{
    private var _lastEnabled : Style = styleRepository.all().first()
    override val lastEnabled: Style get() = _lastEnabled

    private val _enabled : PublishSubject<Style> = PublishSubject.create()
    override val enabled: Subject<Style> get() = _enabled

    override fun enable(style: Style) {
        _lastEnabled = style
        enabled.onNext(style)
    }
}