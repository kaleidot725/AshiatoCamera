package kaleidot725.michetimer.model.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.highestpeaks.model.data.Picture
import java.io.File
import java.lang.IllegalStateException

class PictureRepositoryImpl(path : String) : PictureRepository {
    private val path  = path
    private var list = ObservableArrayList<Picture>()

    override fun all() : List<Picture> {
        update()
        return this.list.toList()
    }

    override fun count() : Int {
        update()
        return this.list.count()
    }

    private fun update() {
        list.clear()
        File(path).walkTopDown().forEach {
            if (it.path != path) {
                list.add(
                    Picture(
                        Picture.createID(),
                        it.path,
                        it.name)
                )
            }
        }
    }
}