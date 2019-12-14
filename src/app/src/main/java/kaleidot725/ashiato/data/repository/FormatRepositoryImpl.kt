package kaleidot725.ashiato.data.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.data.service.picture.Format
import kaleidot725.ashiato.data.service.picture.FormatType

class FormatRepositoryImpl() : FormatRepository {
    private val list: ArrayList<Format> = ObservableArrayList<Format>()

    init {
        list.add(Format(FormatType.Date, "Date"))
        list.add(Format(FormatType.Time, "Time"))
        list.add(Format(FormatType.Address, "Address"))
        list.add(Format(FormatType.Altitude, "Alt"))
        list.add(Format(FormatType.Latitude, "Lat"))
        list.add(Format(FormatType.Longitude, "Lon"))
    }

    override fun all(): List<Format> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}