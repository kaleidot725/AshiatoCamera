package kaleidot725.ashiato.di.service.picture

import kaleidot725.ashiato.di.data.FormatType
import java.util.*

interface FormatEditor {
    fun setDate(date: Date)
    fun setLocation(altitude: Double, latitude: Double, longitude: Double)

    fun create(): String
    fun enabled(type: FormatType): Boolean
    fun enable(type: FormatType, value: Boolean)
}