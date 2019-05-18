package kaleidot725.highestpeaks.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.MyApplicationNavigator
import kaleidot725.michetimer.model.repository.PictureRepository


class HistoryViewModelFactory(
    private val navigator : MyApplicationNavigator,
    private val repository : PictureRepository)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == HistoryViewModel::class.java) {
            return HistoryViewModel(navigator, repository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}