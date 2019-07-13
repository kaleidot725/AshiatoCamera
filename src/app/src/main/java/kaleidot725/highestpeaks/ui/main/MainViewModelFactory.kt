package kaleidot725.highestpeaks.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception

class MainViewModelFactory(val navigator: MainNavigator) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(navigator) as  T
        }

        throw Exception("have created unknown class type")
    }
}