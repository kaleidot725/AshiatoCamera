package kaleidot725.ashiato.ui.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.PictureRepository

class PreviewViewModelFactory(val repository: PictureRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PreviewViewModel::class.java) {
            return PreviewViewModel(repository) as T
        }

        throw Exception("have created unknown class type")
    }
}