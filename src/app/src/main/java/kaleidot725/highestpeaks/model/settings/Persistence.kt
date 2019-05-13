package kaleidot725.todo.model.persistence

interface Persistence<T> {
    fun save(list: T)
    fun load() : T
}