package kaleidot725.ashiato.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.di.data.Position
import kaleidot725.ashiato.di.data.PositionType

class PositionRepositoryImpl() : PositionRepository {
    private val list: ArrayList<Position> = ObservableArrayList<Position>()

    init {
        list.add(Position(PositionType.TopLeft, "TopLeft"))
        list.add(Position(PositionType.TopCenter, "TopCenter"))
        list.add(Position(PositionType.TopRight, "TopRight"))
        list.add(Position(PositionType.CenterLeft, "CenterLeft"))
        list.add(Position(PositionType.Center, "Center"))
        list.add(Position(PositionType.CenterRight, "CenterRight"))
        list.add(Position(PositionType.BottomLeft, "BottomLeft"))
        list.add(Position(PositionType.BottomCenter, "BottomCenter"))
        list.add(Position(PositionType.BottomRight, "BottomRight"))
    }

    override fun all(): List<Position> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}