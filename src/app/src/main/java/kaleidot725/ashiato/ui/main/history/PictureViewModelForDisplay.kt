package kaleidot725.ashiato.ui.main.history

import android.view.View
import kaleidot725.ashiato.di.data.Picture
import kaleidot725.ashiato.di.repository.PictureRepository
import kaleidot725.ashiato.ui.main.MainNavigator

class PictureViewModelForDisplay(
    private val navigation: MainNavigator,
    private val actor: HistoryFragmentActor,
    private val pictureRepository: PictureRepository,
    private val picture: Picture
) : PictureViewModelBase(navigation, actor, pictureRepository, picture) {

    override fun click(view: View) {
        pictureRepository.preview(this.picture)
        navigation.navigatePreview()
    }

    override fun longClick(view: View): Boolean {
        pictureRepository.action(this.picture)
        actor.action()
        return true
    }
}





