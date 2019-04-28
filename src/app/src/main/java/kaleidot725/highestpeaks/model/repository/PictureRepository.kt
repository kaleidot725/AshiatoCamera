package kaleidot725.michetimer.model.repository

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import kaleidot725.highestpeaks.model.repository.Picture

class PictureRepository(path : String) : Repository<Picture> {
    private val path : String = path
    private var list : ObservableList<Picture> = ObservableArrayList()

    init {
        //
    }

    override fun all() : List<Picture> {
        return this.list
    }

    override fun getById(id : String) : Picture {
        return this.list[0]
    }

    override fun add(item: Picture) {

    }

    override fun remove(item : Picture) {

    }

    override fun update(item: Picture) {

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