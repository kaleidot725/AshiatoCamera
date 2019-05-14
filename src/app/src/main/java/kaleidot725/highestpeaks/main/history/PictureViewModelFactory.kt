package kaleidot725.highestpeaks.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.model.data.Picture

class PictureViewModelFactory(picture : Picture) : ViewModelProvider.Factory {
    private val picture : Picture = picture

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PictureViewModel::class.java) {
            return PictureViewModel(picture) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}