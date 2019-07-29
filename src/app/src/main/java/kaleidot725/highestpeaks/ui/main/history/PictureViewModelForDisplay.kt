package kaleidot725.highestpeaks.ui.main.history

import android.view.View
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository

class PictureViewModelForDisplay(
    private val navigation : MainNavigator,
    private val actor : HistoryFragmentActor,
    private val pictureRepository: PictureRepository,
    private val picture : Picture
) : PictureViewModelBase(navigation, actor, pictureRepository, picture) {

    override fun click(view : View)  {
        pictureRepository.preview(this.picture)
        navigation.navigatePreview()
    }

    override  fun longClick(view : View) : Boolean {
        pictureRepository.action(this.picture)
        actor.action()
        return true
    }
}





