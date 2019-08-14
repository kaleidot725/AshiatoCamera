package kaleidot725.ashiato.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception

class EditViewModelFactory(val navigator : EditNavigator) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == EditViewModel::class.java) {
            return EditViewModel(navigator) as  T
        }

        throw Exception("have created unknown class type")
    }
}