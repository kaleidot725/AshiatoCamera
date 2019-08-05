package kaleidot725.highestpeaks.ui.edit.style

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StyleRecyclerViewModelFactory : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == StyleRecyclerViewModel::class.java) {
            return StyleRecyclerViewModel() as T
        }

        throw IllegalArgumentException("undefined class")
    }
}
