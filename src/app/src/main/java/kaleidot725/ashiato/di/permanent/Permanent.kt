package kaleidot725.ashiato.di.permanent

interface Permanent<T> {
    fun save(list: T)
    fun load(): T
}