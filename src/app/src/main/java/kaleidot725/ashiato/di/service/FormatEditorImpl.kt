package kaleidot725.ashiato.di.service

import kaleidot725.ashiato.di.data.FormatType
import kaleidot725.ashiato.di.repository.DateTimeRepository
import kaleidot725.ashiato.di.repository.LocationRepository
import java.text.SimpleDateFormat

class FormatEditorImpl(
    private val dateTimeRepository: DateTimeRepository,
    private val locationRepository: LocationRepository
) :
    FormatEditor
{
    private var dateEnable: Boolean = false
    private var timeEnable: Boolean = false
    private var altitudeEnable: Boolean = false
    private var latitudeEnable: Boolean = false
    private var longitudeEnable: Boolean = false

    private val space : String = " "
    private val dateFormat : SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
    private val timeFormat : SimpleDateFormat = SimpleDateFormat("HH:mm:ss")

    override fun create(): String {
        var value = ""

        if (dateEnable) {
            value += dateFormat.format(dateTimeRepository.lastDate) + space
        }

        if (timeEnable) {
            value += timeFormat.format(dateTimeRepository.lastDate) + space
        }

        if (altitudeEnable) {
            value += "${locationRepository.lastAltitude?.toInt()}m" + space
        }

        if (latitudeEnable) {
            value += "${locationRepository.lastLatitude?.toInt()}°" + space
        }

        if (longitudeEnable) {
            value += "${locationRepository.lastLongitude?.toInt()}°" + space
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