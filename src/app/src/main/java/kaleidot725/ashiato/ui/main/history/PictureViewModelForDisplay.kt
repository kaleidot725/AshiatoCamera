package kaleidot725.ashiato.ui.main.history

import android.view.View
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.PictureRepository
import kaleidot725.ashiato.data.service.picture.Picture
import kotlinx.coroutines.launch

class PictureViewModelForDisplay(
    private val pictureRepository: PictureRepository,
    private val p: Picture,
    private val clickEvent: (() -> Unit)? = null,
    private val longClickEvent: (() -> Unit)? = null
) : PictureViewModelBase(pictureRepository, p) {

    override fun click(view: View) {
        viewModelScope.launch {
            clickEvent?.invoke()
        }
    }

    override fun longClick(view: View): Boolean {
        viewModelScope.launch {
            longClickEvent?.invoke()
        }
        return true
    }
}





