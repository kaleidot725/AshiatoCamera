package kaleidot725.highestpeaks.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.MyApplicationNavigator
import kaleidot725.highestpeaks.model.data.Picture

class PictureViewModelFactory(
    private val navigator : MyApplicationNavigator,
    private val picture : Picture)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PictureViewModel::class.java) {
            return PictureViewModel(navigator, picture) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}