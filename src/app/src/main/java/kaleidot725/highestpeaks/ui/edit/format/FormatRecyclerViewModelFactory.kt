package kaleidot725.highestpeaks.ui.edit.format

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.data.Format

class FormatRecyclerViewModelFactory(val format : Format)  : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == FormatRecyclerViewModel::class.java) {
            return FormatRecyclerViewModel(format) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}
