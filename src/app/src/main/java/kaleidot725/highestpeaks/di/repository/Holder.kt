package kaleidot725.highestpeaks.di.repository

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

interface Holder<T> {
    val lastedValue : T
    val subject : Subject<T>
    fun update(t : T)
}