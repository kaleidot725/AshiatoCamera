package kaleidot725.ashiato.di.persistence

interface Persistence<T> {
    fun save(list: T)
    fun load(): T
}