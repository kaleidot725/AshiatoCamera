package kaleidot725.ashiato.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.di.service.picture.Format
import kaleidot725.ashiato.di.service.picture.FormatType

class FormatRepositoryImpl() : FormatRepository {
    private val list: ArrayList<Format> = ObservableArrayList<Format>()

    init {
        list.add(Format(FormatType.Date, "Date"))
        list.add(Format(FormatType.Time, "Time"))
//        list.add(Format(FormatType.Weather, "Weather"))
        list.add(Format(FormatType.Address, "Address"))
        list.add(Format(FormatType.Altitude, "Altitude"))
        list.add(Format(FormatType.Latitude, "Latitude"))
        list.add(Format(FormatType.Longitude, "Longitude"))
    }

    override fun all(): List<Format> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}