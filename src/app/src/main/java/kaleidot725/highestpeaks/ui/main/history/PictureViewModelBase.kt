package kaleidot725.highestpeaks.ui.main.history

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.di.holder.Holder
import kaleidot725.highestpeaks.di.data.Picture

abstract class PictureViewModelBase(
    private val navigation : MainNavigator,
    private val actor : HistoryFragmentActor,
    private val picture : Picture,
    private val selected : Holder<Picture>
) : ViewModel() {

    internal val _name : MutableLiveData<String> = MutableLiveData()
    val name : LiveData<String> = _name

    internal val _path : MutableLiveData<String> = MutableLiveData()
    val path : LiveData<String> get() = _path

    internal val _isChecked : MutableLiveData<Boolean> = MutableLiveData()
    val isChecked : LiveData<Boolean> = _isChecked

    init {
        _path.value = picture.path
        _name.value = picture.name
        _isChecked.value = false
    }

    abstract fun click(view : View)
    abstract fun longClick(view : View) : Boolean
}





