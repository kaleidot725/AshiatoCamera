package kaleidot725.highestpeaks.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.highestpeaks.di.data.Format
import kaleidot725.highestpeaks.di.data.FormatType
import kaleidot725.highestpeaks.di.data.Style

class StyleRepositoryImpl() : StyleRepository {
    private val list : ArrayList<Style> = ObservableArrayList<Style>()

    init {
        list.add(Style(16, "16dp"))
        list.add(Style(20, "20dp"))
        list.add(Style(24, "24dp"))
        list.add(Style(28, "28dp"))
        list.add(Style(32, "32dp"))
    }

    override fun all(): List<Style> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}