package kaleidot725.highestpeaks.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture

class PictureViewModelFactory(
    private val navigator : MainNavigator,
    private val picture : Picture,
    private val preview : Holder<Picture>)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PictureViewModel::class.java) {
            return PictureViewModel(navigator, picture, preview) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}