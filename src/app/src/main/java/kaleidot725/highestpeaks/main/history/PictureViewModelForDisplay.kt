package kaleidot725.highestpeaks.main.history

import android.view.View
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture

class PictureViewModelForDisplay(
    private val navigation : MainNavigator,
    private val actor : HistoryFragmentActor,
    private val picture : Picture,
    private val selected : Holder<Picture>) : PictureViewModelBase(navigation, actor, picture, selected) {

    override fun click(view : View)  {
        selected.value = this.picture
        navigation.navigatePreview()
    }

    override  fun longClick(view : View) : Boolean {
        selected.value = this.picture
        actor.action()
        return true
    }
}





