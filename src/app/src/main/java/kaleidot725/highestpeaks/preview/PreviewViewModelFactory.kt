package kaleidot725.highestpeaks.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository
import java.lang.Exception

class PreviewViewModelFactory(val repository : PictureRepository, val preview : Holder<Picture>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PreviewViewModel::class.java) {
            return PreviewViewModel(repository, preview) as  T
        }

        throw Exception("have created unknown class type")
    }
}