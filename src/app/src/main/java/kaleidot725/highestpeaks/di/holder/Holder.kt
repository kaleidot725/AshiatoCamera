package kaleidot725.highestpeaks.di.holder

import io.reactivex.subjects.Subject

interface Holder<T> {
    val lastedValue : T
    val subject : Subject<T>
    fun update(t : T)
}