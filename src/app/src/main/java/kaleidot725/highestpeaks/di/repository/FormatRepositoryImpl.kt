package kaleidot725.highestpeaks.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.highestpeaks.di.data.Developer
import kaleidot725.highestpeaks.di.data.Format

class FormatRepositoryImpl() : FormatRepository {
    private val list : ArrayList<Format> = ObservableArrayList<Format>()

    init {
        list.add(Format(0, "Date"))
        list.add(Format(1, "Time"))
        list.add(Format(2, "Alt"))
        list.add(Format(3, "Lat"))
        list.add(Format(4, "Lng"))
    }

    override fun all(): List<Format> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}