package kaleidot725.michetimer.model.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.highestpeaks.model.repository.Picture
import java.io.File
import java.lang.IllegalStateException
import java.security.InvalidKeyException

class DefaultPictureRepository(path : String) : PictureRepository<Picture> {
    private val path  = path
    private var list = ObservableArrayList<Picture>()
    private var initialized : Boolean = false

    override fun init() {
        File(path).walkTopDown().forEach {
            if (it.path != path) {
                list.add(Picture(Picture.createID(),it.path, it.name))
            }
        }

        initialized = true
    }

    override fun all() : List<Picture> {
        if (!initialized) {
            throw IllegalStateException("not initialized")
        }

        return this.list.toList()
    }

    override fun count() : Int {
        if (!initialized) {
            throw IllegalStateException("not initialized")
        }

        return this.list.count()
    }
}