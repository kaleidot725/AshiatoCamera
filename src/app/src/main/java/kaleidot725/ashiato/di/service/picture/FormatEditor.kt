package kaleidot725.ashiato.di.service.picture

import java.util.*

interface FormatEditor {
    fun set(date : Date, altitude: Double, latitude: Double, longitude: Double, address : String, weather : String)
    fun create(): String
    fun enabled(type: FormatType): Boolean
    fun enable(type: FormatType, value: Boolean)
    fun enableAll(value : Boolean)
}