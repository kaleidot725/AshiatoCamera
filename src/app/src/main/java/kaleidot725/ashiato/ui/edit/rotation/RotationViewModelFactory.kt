package kaleidot725.ashiato.ui.edit.rotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.AngleRepository
import kaleidot725.ashiato.di.service.PictureEditor
import kaleidot725.ashiato.di.service.RotationEditor
import java.lang.IllegalArgumentException

class RotationViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val rotationEditor: RotationEditor,
    private val angleRepository : AngleRepository
) : ViewModelProvider.Factory
{
    @Suppress("UNCHEKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RotationViewModel::class.java) {
            return RotationViewModel(pictureEditor, rotationEditor, angleRepository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}