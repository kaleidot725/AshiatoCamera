package kaleidot725.highestpeaks.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.highestpeaks.ui.main.MainNavigator
import java.lang.Exception

class EditViewModelFactory(
    val navigator : EditNavigator,
    val locationRepository: LocationRepository,
    val editPicture : Holder<Picture>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == EditViewModel::class.java) {
            return EditViewModel(navigator, locationRepository, editPicture) as  T
        }

        throw Exception("have created unknown class type")
    }
}