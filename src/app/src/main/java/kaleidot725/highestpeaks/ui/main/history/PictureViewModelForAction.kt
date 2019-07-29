package kaleidot725.highestpeaks.ui.main.history

import android.view.View
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository

class PictureViewModelForAction(private val navigation : MainNavigator,
                                private val actor : HistoryFragmentActor,
                                private val pictureRepository: PictureRepository,
                                private val picture : Picture
) : PictureViewModelBase(navigation, actor, pictureRepository, picture) {

    init {
        _isChecked.value = pictureRepository.actioned!!.path == picture.path
    }

    override fun click(view : View)  {
        _isChecked.value = !(isChecked.value ?: true)
    }

    override fun longClick(view: View): Boolean {
        return true
    }
}
