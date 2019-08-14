package kaleidot725.ashiato.di.service

import kaleidot725.ashiato.di.data.FormatType

interface FormatEditor {
    fun create() : String
    fun enabled(type : FormatType) : Boolean
    fun enable(type: FormatType, value : Boolean)
}