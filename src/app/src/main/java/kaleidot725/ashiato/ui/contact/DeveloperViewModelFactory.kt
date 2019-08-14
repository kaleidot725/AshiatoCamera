package kaleidot725.ashiato.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.data.Developer

class DeveloperViewModelFactory(developer : Developer) : ViewModelProvider.Factory {
    private val developer : Developer = developer

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == DeveloperViewModel::class.java) {
            return DeveloperViewModel(developer) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}