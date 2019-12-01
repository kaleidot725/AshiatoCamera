package kaleidot725.ashiato.ui.edit.position

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.data.repository.PositionRepository
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.PositionEditor

class PositionViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val positionEditor: PositionEditor,
    private val positionRepository: PositionRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PositionViewModel::class.java) {
            return PositionViewModel(pictureEditor, positionEditor, positionRepository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}