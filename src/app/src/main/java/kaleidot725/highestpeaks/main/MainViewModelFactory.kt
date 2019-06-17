package kaleidot725.highestpeaks.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.model.data.Holder
import java.lang.Exception

class MainViewModelFactory(val navigator: MainNavigator) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(navigator) as  T
        }

        throw Exception("have created unknown class type")
    }
}