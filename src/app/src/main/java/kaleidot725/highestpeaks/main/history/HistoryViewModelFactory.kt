package kaleidot725.highestpeaks.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository


class HistoryViewModelFactory(
    private val navigator : MainNavigator,
    private val actor : HistoryFragmentActor,
    private val repository : PictureRepository,
    private val selected : Holder<Picture>
)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == HistoryViewModel::class.java) {
            return HistoryViewModel(navigator, actor, repository, selected) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}