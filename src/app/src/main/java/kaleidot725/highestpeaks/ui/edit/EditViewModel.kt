package kaleidot725.highestpeaks.ui.edit

import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.highestpeaks.ui.main.MainNavigator
import java.util.*

class EditViewModel(val locationRepository: LocationRepository, val editPicture : Holder<Picture>) : ViewModel() {

    val editPath : String = editPicture.value.path
    val editText : String = "${Date().toString()}    ${locationRepository.lastAltitude?.toInt()}m"

    override fun onCleared() {
        super.onCleared()
    }
}