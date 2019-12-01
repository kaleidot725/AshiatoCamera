package kaleidot725.ashiato.data.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.data.service.picture.Angle

class AngleRepositoryImpl : AngleRepository {
    private val list: ArrayList<Angle> = ObservableArrayList<Angle>()

    init {
        list.add(Angle(0f, "0°"))
        list.add(Angle(90f, "90°"))
        list.add(Angle(180f, "180°"))
        list.add(Angle(270f, "270°"))
    }

    override fun all(): List<Angle> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}