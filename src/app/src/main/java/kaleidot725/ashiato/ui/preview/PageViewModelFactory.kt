package kaleidot725.ashiato.ui.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.PictureRepository

class PageViewModelFactory(val repository: PictureRepository, val position: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PageViewModel::class.java) {
            return PageViewModel(repository, position) as T
        }

        throw Exception("have created unknown class type")
    }
}