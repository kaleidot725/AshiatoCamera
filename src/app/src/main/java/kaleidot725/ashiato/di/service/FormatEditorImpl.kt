package kaleidot725.ashiato.di.service

import kaleidot725.ashiato.di.data.FormatType
import kaleidot725.ashiato.di.repository.DateTimeRepository
import kaleidot725.ashiato.di.repository.LocationRepository
import java.text.SimpleDateFormat
import java.util.*

class FormatEditorImpl : FormatEditor
{
    private var dateEnable: Boolean = false
    private var timeEnable: Boolean = false
    private var altitudeEnable: Boolean = false
    private var latitudeEnable: Boolean = false
    private var longitudeEnable: Boolean = false

    private val space : String = " "
    private val dateFormat : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
    private val timeFormat : SimpleDateFormat = SimpleDateFormat("HH:mm:ss")

    private var date : Date= Date()
    private var altitude : Double = 0.0
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0

    override fun setDate(date : Date) {
        this.date = date
    }

    override fun setLocation(altitude: Double, latitude: Double, longitude: Double) {
        this.altitude = altitude
        this.latitude = latitude
        this.longitude = longitude
    }

    override fun create(): String {
        var value = ""

        if (dateEnable) {
            value += dateFormat.format(date) + space
        }

        if (timeEnable) {
            value += timeFormat.format(date) + space
        }

        if (altitudeEnable) {
            value += "${altitude.toInt()}m" + space
        }

        if (latitudeEnable) {
            value += "${latitude.toInt()}°" + space
        }

        if (longitudeEnable) {
            value += "${longitude.toInt()}°" + space
        }

        return value
    }

    override fun enable(type: FormatType, value : Boolean) {
        when(type) {
            FormatType.Date -> dateEnable = value
            FormatType.Time -> timeEnable = value
            FormatType.Altitude -> altitudeEnable = value
            FormatType.Latitude -> latitudeEnable = value
            FormatType.Longitude -> longitudeEnable = value
        }
    }

    override fun enabled(type: FormatType): Boolean {
        when(type) {
            FormatType.Date -> return dateEnable
            FormatType.Time -> return timeEnable
            FormatType.Altitude -> return altitudeEnable
            FormatType.Latitude -> return latitudeEnable
            FormatType.Longitude -> return longitudeEnable
        }
    }
}