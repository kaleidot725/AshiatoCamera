package kaleidot725.ashiato.ui.edit.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.service.FormatEditor
import kaleidot725.ashiato.di.service.PictureEditor
import kaleidot725.ashiato.ui.edit.EditNavigator
import kaleidot725.michetimer.model.repository.PictureRepository
import java.lang.Exception

class ConfirmViewModelFactory(
    val navigator : EditNavigator,
    val pictureRepository: PictureRepository,
    val formatEditor : FormatEditor,
    val bitmapEditor : PictureEditor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ConfirmViewModel::class.java) {
            return ConfirmViewModel(navigator, pictureRepository, formatEditor, bitmapEditor) as  T
        }

        throw Exception("have created unknown class type")
    }
}