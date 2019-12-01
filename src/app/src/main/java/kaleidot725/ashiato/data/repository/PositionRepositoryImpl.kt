package kaleidot725.ashiato.data.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.data.service.picture.Position
import kaleidot725.ashiato.data.service.picture.PositionType

class PositionRepositoryImpl() : PositionRepository {
    private val list: ArrayList<Position> = ObservableArrayList<Position>()

    init {
        list.add(Position(PositionType.TopLeft, "Top\nLeft"))
        list.add(Position(PositionType.TopCenter, "Top\nCenter"))
        list.add(Position(PositionType.TopRight, "Top\nRight"))
        list.add(Position(PositionType.CenterLeft, "Center\nLeft"))
        list.add(Position(PositionType.Center, "Center"))
        list.add(Position(PositionType.CenterRight, "Center\nRight"))
        list.add(Position(PositionType.BottomLeft, "Bottom\nLeft"))
        list.add(Position(PositionType.BottomCenter, "Bottom\nCenter"))
        list.add(Position(PositionType.BottomRight, "Bottom\nRight"))
    }

    override fun all(): List<Position> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}