package kaleidot725.ashiato.ui.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.ui.main.MainNavigator
import kaleidot725.ashiato.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository

class PictureViewModelFactory(
    private val navigator : MainNavigator,
    private val actor : HistoryFragmentActor,
    private val pictureRepository: PictureRepository,
    private val picture : Picture
)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PictureViewModelForDisplay::class.java) {
            return PictureViewModelForDisplay(navigator, actor, pictureRepository, picture) as T
        }

        if (modelClass == PictureViewModelForAction::class.java) {
            return PictureViewModelForAction(navigator, actor, pictureRepository, picture) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}