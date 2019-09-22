package kaleidot725.ashiato.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.PictureRepository

class MainViewModelFactory(private val navigator: MainNavigator,
                           private val pictureRepository: PictureRepository)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(navigator, pictureRepository) as T
        }

        throw Exception("have created unknown class type")
    }
}