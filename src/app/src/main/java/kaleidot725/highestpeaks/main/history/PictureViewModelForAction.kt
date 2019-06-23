package kaleidot725.highestpeaks.main.history

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture

class PictureViewModelForAction(private val navigation : MainNavigator,
                                private val actor : HistoryFragmentActor,
                                private val picture : Picture,
                                private val selected : Holder<Picture>
) : PictureViewModelBase(navigation, actor, picture, selected) {

    init {
        _isChecked.value = selected.value.path == picture.path
    }

    override fun click(view : View)  {
        _isChecked.value = !(isChecked.value ?: true)
    }

    override fun longClick(view: View): Boolean {
        return true
    }
}
