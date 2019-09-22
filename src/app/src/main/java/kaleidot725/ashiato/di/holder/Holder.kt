package kaleidot725.ashiato.di.holder

import io.reactivex.subjects.Subject

interface Holder<T> {
    val lastedValue: T
    val subject: Subject<T>
    fun update(t: T)
}