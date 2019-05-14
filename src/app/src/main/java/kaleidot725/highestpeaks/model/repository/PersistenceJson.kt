package kaleidot725.todo.model.persistence

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.File
import java.lang.reflect.ParameterizedType

open class PersistenceJson<T>(name : String, clazz : Class<T>) : Persistence<T> {
    val name: String = name

    private val moshi: Moshi = Moshi.Builder().build()
    private val type: ParameterizedType = Types.newParameterizedType(List::class.java, clazz)
    private val adapter: JsonAdapter<List<T>> = moshi.adapter(type)

    override fun save(item: T) {
        try {
            val json = adapter.toJson(arrayListOf(item))
            val file = File(name)
            file.absoluteFile.bufferedWriter().use { it.write(json) }
        } catch (e: Exception) {
            Log.d(this.javaClass.name.toString(), e.toString())
            throw e
        }
    }

    override fun load(): T {
        try {
            val file = File(name)
            if (file.exists()) {
                val contents = file.absoluteFile.bufferedReader().use { it.readText() }
                return adapter.fromJson(contents)?.first() ?: throw java.lang.Exception("Unexpected Error")
            }
            throw java.lang.Exception("Unexpected Error")
        } catch (e: Exception) {
            Log.d(this.javaClass.name.toString(), e.toString())
            throw e
        }
    }
}