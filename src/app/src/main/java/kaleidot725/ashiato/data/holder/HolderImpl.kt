package kaleidot725.ashiato.data.holder

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class HolderImpl<T>(t: T) : Holder<T> {
    private var _lastedValue: T = t
    override val lastedValue get() = _lastedValue

    private val _subject: PublishSubject<T> = PublishSubject.create()
    override val subject: Subject<T> get() = _subject

    init {
        subject.onNext(t)
    }

    override fun update(t: T) {
        _lastedValue = t
        subject.onNext(t)
    }
}