package kaleidot725.highestpeaks.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.ui.main.MainNavigator
import java.lang.Exception

class EditViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == EditViewModel::class.java) {
            return EditViewModel() as  T
        }

        throw Exception("have created unknown class type")
    }
}