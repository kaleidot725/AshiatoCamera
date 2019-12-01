package kaleidot725.ashiato.ui.main.history

import android.view.View
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.PictureRepository
import kaleidot725.ashiato.data.service.picture.Picture
import kaleidot725.ashiato.ui.main.MainNavigator
import kotlinx.coroutines.launch

class PictureViewModelForDisplay(
    private val navigation: MainNavigator,
    private val actor: HistoryFragmentActor,
    private val pictureRepository: PictureRepository,
    private val picture: Picture
) : PictureViewModelBase(navigation, actor, pictureRepository, picture) {

    override fun click(view: View) {
        viewModelScope.launch {
            pictureRepository.preview(picture)
            navigation.navigatePreview()
        }
    }

    override fun longClick(view: View): Boolean {
        viewModelScope.launch {
            pictureRepository.action(picture)
            actor.action()
        }
        return true
    }
}





