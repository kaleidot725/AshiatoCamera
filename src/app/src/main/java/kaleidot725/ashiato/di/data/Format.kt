package kaleidot725.ashiato.di.data

enum class FormatType {
    Date,
    Time,
    Altitude,
    Latitude,
    Longitude,
}

data class Format(val type : FormatType, val detail : String)


