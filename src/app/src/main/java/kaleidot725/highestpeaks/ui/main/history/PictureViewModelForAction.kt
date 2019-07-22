package kaleidot725.highestpeaks.ui.main.history

import android.view.View
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.di.repository.Holder
import kaleidot725.highestpeaks.di.data.Picture

class PictureViewModelForAction(private val navigation : MainNavigator,
                                private val actor : HistoryFragmentActor,
                                private val picture : Picture,
                                private val selected : Holder<Picture>
) : PictureViewModelBase(navigation, actor, picture, selected) {

    init {
        _isChecked.value = selected.lastedValue.path == picture.path
    }

    override fun click(view : View)  {
        _isChecked.value = !(isChecked.value ?: true)
    }

    override fun longClick(view: View): Boolean {
        return true
    }
}
