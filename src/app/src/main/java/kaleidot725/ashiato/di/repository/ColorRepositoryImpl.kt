package kaleidot725.ashiato.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.di.data.Color

class ColorRepositoryImpl() : ColorRepository {
    private val list : ArrayList<Color> = ObservableArrayList<Color>()


    init {
        list.add(Color(android.graphics.Color.WHITE, "White"))
        list.add(Color(android.graphics.Color.BLACK, "Black"))
        list.add(Color(android.graphics.Color.BLUE, "Blue"))
        list.add(Color(android.graphics.Color.CYAN, "Cyan"))
        list.add(Color(android.graphics.Color.DKGRAY, "DarkGray"))
        list.add(Color(android.graphics.Color.GRAY, "Gray"))
        list.add(Color(android.graphics.Color.GREEN, "Green"))
        list.add(Color(android.graphics.Color.LTGRAY, "LightGray"))
        list.add(Color(android.graphics.Color.MAGENTA, "Magenta"))
        list.add(Color(android.graphics.Color.RED, "Red"))
        list.add(Color(android.graphics.Color.YELLOW, "Yellow"))
    }

    override fun all(): List<Color> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}