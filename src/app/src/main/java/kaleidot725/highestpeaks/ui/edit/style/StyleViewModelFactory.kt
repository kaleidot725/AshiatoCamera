package kaleidot725.highestpeaks.ui.edit.style

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.repository.FormatRepository
import kaleidot725.highestpeaks.di.service.FormatEditor
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.ui.main.history.HistoryViewModel

class StyleViewModelFactory : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == StyleViewModel::class.java) {
            return StyleViewModel() as T
        }

        throw IllegalArgumentException("undefined class")
    }
}
