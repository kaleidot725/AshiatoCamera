package kaleidot725.michetimer.model.repository

import androidx.databinding.ObservableList
import kaleidot725.highestpeaks.model.repository.Picture

interface Repository<T>  {

    fun all() : List<Picture>
    fun getById(id : String) : T

    fun add(item : T)
    fun remove(item : T)
    fun update(item : T)
    fun count() : Int

    fun addOnListChangedCallback(callback : ObservableList.OnListChangedCallback<ObservableList<T>>)
    fun removeOnListChangedCallback(callback : ObservableList.OnListChangedCallback<ObservableList<T>>)
}