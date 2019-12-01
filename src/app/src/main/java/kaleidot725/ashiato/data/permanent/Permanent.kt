package kaleidot725.ashiato.data.permanent

interface Permanent<T> {
    fun save(list: T)
    fun load(): T
}