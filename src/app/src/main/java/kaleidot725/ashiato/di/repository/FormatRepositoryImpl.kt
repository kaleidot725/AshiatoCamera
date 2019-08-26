package kaleidot725.ashiato.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.di.data.Format
import kaleidot725.ashiato.di.data.FormatType

class FormatRepositoryImpl() : FormatRepository {
    private val list: ArrayList<Format> = ObservableArrayList<Format>()

    init {
        list.add(Format(FormatType.Date, "Date"))
        list.add(Format(FormatType.Time, "Time"))
        list.add(Format(FormatType.Altitude, "Altitude"))
        list.add(Format(FormatType.Latitude, "Latitude"))
        list.add(Format(FormatType.Longitude, "Lngitude"))
    }

    override fun all(): List<Format> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}