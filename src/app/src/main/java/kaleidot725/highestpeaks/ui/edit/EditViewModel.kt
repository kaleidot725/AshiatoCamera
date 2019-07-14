package kaleidot725.highestpeaks.ui.edit

import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.ui.main.MainNavigator

class EditViewModel(val editPicture : Holder<Picture>) : ViewModel() {

    val edit : Picture = editPicture.value

    init {

    }

    override fun onCleared() {
        super.onCleared()
    }
}