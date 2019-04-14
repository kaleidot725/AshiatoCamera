package kaleidot725.michetimer.model.repository

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import kaleidot725.highestpeaks.model.entity.Picture

class PictureRepository(path : String) : Repository<Picture> {
    private val path : String = path
    private var list : ObservableList<Picture> = ObservableArrayList()

    init {
        //
    }

    override fun add(item: Picture) {
    }

    override fun remove(item : Picture) {
    }

    override fun update(item: Picture) {
    }

    override fun next() : Int {
        return 0
    }

    override fun findById(id: Int): Picture? {
        return null
    }

    override fun findAll() : List<Picture> {
        return this.list
    }

    override fun count() : Int {
        return this.list.count()
    }

    override fun addOnListChangedCallback(callback : ObservableList.OnListChangedCallback<ObservableList<Picture>>) {
        this.list.addOnListChangedCallback(callback)
    }

    override fun removeOnListChangedCallback(callback : ObservableList.OnListChangedCallback<ObservableList<Picture>>){
        this.list.removeOnListChangedCallback(callback)
    }
}