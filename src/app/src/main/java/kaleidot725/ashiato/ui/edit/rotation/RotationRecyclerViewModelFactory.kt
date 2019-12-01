package kaleidot725.ashiato.ui.edit.rotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.data.service.picture.Angle
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.RotationEditor

class RotationRecyclerViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val rotationEditor: RotationEditor,
    private val angle: Angle
) : ViewModelProvider.Factory {
    @Suppress("UNCHEKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RotationRecyclerViewModel::class.java) {
            return RotationRecyclerViewModel(pictureEditor, rotationEditor, angle) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}