package kaleidot725.ashiato.ui.main.history

import android.view.View
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.di.repository.PictureRepository
import kaleidot725.ashiato.di.service.picture.Picture
import kaleidot725.ashiato.ui.main.MainNavigator
import kotlinx.coroutines.launch

class PictureViewModelForAction(
    private val navigation: MainNavigator,
    private val actor: HistoryFragmentActor,
    private val pictureRepository: PictureRepository,
    private val picture: Picture
) : PictureViewModelBase(navigation, actor, pictureRepository, picture) {

    init {
        _isChecked.value = pictureRepository.actioned!!.path == picture.path
    }

    override fun click(view: View) {
        viewModelScope.launch {
            _isChecked.value = !(isChecked.value ?: true)
        }
    }

    override fun longClick(view: View): Boolean {
        return true
    }
}
