package kaleidot725.highestpeaks.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.michetimer.model.repository.PictureRepository


class HistoryViewModelFactory(repository : PictureRepository) : ViewModelProvider.Factory {
    private val repository : PictureRepository = repository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == HistoryViewModel::class.java) {
            return HistoryViewModel(repository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}