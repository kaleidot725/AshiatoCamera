package kaleidot725.ashiato.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.di.data.Format
import kaleidot725.ashiato.di.data.FormatType

class FormatRepositoryImpl() : FormatRepository {
    private val list : ArrayList<Format> = ObservableArrayList<Format>()

    init {
        list.add(Format(FormatType.Date, "Date"))
        list.add(Format(FormatType.Time, "Time"))
        list.add(Format(FormatType.Altitude, "Alt"))
        list.add(Format(FormatType.Latitude, "Lat"))
        list.add(Format(FormatType.Longitude, "Lng"))
    }

    override fun all(): List<Format> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}