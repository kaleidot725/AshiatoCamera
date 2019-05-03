package kaleidot725.highestpeaks.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.model.repository.Developer

class DeveloperViewModelFactory(developer : Developer) : ViewModelProvider.Factory {
    private val developer : Developer = developer

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == DeveloperViewModel::class.java) {
            return DeveloperViewModel(developer) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}