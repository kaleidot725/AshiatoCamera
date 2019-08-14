package kaleidot725.ashiato.ui.edit.position

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.data.Position
import kaleidot725.ashiato.di.service.PictureEditor
import kaleidot725.ashiato.di.service.PositionEditor

class PositionRecyclerViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val positionEditor: PositionEditor,
    private val position : Position
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PositionRecyclerViewModel::class.java) {
            return PositionRecyclerViewModel(pictureEditor, positionEditor, position) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}