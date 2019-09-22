package kaleidot725.ashiato.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.di.service.picture.Color

class ColorRepositoryImpl() : ColorRepository {
    private val list: ArrayList<Color> = ObservableArrayList<Color>()

    init {
        list.add(Color(android.graphics.Color.rgb(255, 255, 255), "White"))
        list.add(Color(android.graphics.Color.rgb(220, 159, 180), "Nadeshiko"))
        list.add(Color(android.graphics.Color.rgb(240, 94, 28), "Ohni"))
        list.add(Color(android.graphics.Color.rgb(255, 177, 27), "Yamabuki"))
        list.add(Color(android.graphics.Color.rgb(131, 138, 45), "Koke"))
        list.add(Color(android.graphics.Color.rgb(129, 199, 212), "Mizu"))
        list.add(Color(android.graphics.Color.rgb(87, 129, 195), "Fuji"))
        list.add(Color(android.graphics.Color.rgb(193, 50, 142), "Botan"))
        list.add(Color(android.graphics.Color.rgb(130, 130, 130), "Hai"))
        list.add(Color(android.graphics.Color.rgb(28, 28, 28), "Sumi"))
    }

    override fun all(): List<Color> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}