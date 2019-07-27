package kaleidot725.highestpeaks.ui.edit.format

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Format

class FormatRecyclerViewModel(val format : Format) : ViewModel() {

    private val _detail : MutableLiveData<String> = MutableLiveData()
    val detail : LiveData<String> get() = _detail

    init {
        _detail.postValue(format.detail)
    }

    fun click(v : View) {
        Log.v("tag", "ForartRecylcerItemClick")
    }
}