package kaleidot725.highestpeaks.di.service

import kaleidot725.highestpeaks.di.data.FormatType

interface FormatEditor {
    fun create() : String
    fun enabled(type : FormatType) : Boolean
    fun enable(type: FormatType, value : Boolean)
}