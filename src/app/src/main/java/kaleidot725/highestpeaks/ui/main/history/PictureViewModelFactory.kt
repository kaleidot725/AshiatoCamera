package kaleidot725.highestpeaks.ui.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture

class PictureViewModelFactory(
    private val navigator : MainNavigator,
    private val actor : HistoryFragmentActor,
    private val picture : Picture,
    private val selected : Holder<Picture>)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PictureViewModelForDisplay::class.java) {
            return PictureViewModelForDisplay(navigator, actor, picture, selected) as T
        }

        if (modelClass == PictureViewModelForAction::class.java) {
            return PictureViewModelForAction(navigator, actor, picture, selected) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}