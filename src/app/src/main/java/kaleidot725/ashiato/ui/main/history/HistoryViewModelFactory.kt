package kaleidot725.ashiato.ui.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.data.repository.PictureRepository
import kaleidot725.ashiato.ui.main.MainNavigator

class HistoryViewModelFactory(
    private val navigator: MainNavigator,
    private val actor: HistoryFragmentActor,
    private val repository: PictureRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == HistoryViewModel::class.java) {
            return HistoryViewModel(navigator, actor, repository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}