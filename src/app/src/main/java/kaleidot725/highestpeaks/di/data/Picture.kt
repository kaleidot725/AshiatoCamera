package kaleidot725.highestpeaks.di.data

import java.util.*

data class Picture(val id : String, val path : String, val name : String) {
    companion object {
        fun createID() : String = UUID.randomUUID().toString()
    }
}