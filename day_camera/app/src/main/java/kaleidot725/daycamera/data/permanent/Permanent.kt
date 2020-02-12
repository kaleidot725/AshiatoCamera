package kaleidot725.daycamera.data.permanen

interface Permanent<T> {
    fun save(list: T)
    fun load(): T
}