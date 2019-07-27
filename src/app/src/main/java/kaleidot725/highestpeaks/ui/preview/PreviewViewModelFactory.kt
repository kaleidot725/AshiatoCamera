package kaleidot725.highestpeaks.ui.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.holder.Holder
import kaleidot725.michetimer.model.repository.PictureRepository
import java.lang.Exception

class PreviewViewModelFactory(val repository : PictureRepository, val selected : Holder<Picture>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PreviewViewModel::class.java) {
            return PreviewViewModel(repository, selected) as  T
        }

        throw Exception("have created unknown class type")
    }
}