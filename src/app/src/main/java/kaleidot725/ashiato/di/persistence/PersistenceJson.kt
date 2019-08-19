package kaleidot725.ashiato.di.persistence

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.File

open class PersistenceJson<T>(name : String, clazz : Class<T>) : Persistence<T> {
    val name: String = name

    private val moshi: Moshi = Moshi.Builder().build()
    private val adapter: JsonAdapter<T> = moshi.adapter(clazz)

    override fun save(item: T) {
        try {
            val json = adapter.toJson(item)
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
                return adapter.fromJson(contents) ?: throw java.lang.Exception("Unexpected Error")
            }
            throw java.lang.Exception("Unexpected Error")
        } catch (e: Exception) {
            Log.d(this.javaClass.name.toString(), e.toString())
            throw e
        }
    }
}