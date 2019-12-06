package kaleidot725.ashiato.ui.main.history

import android.view.View
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.PictureRepository
import kaleidot725.ashiato.data.service.picture.Picture
import kotlinx.coroutines.launch

class PictureViewModelForAction(
    private val pictureRepository: PictureRepository,
    private val p: Picture,
    private val selected: Boolean
) : PictureViewModelBase(pictureRepository, p) {

    init {
        _isChecked.value = selected
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
