package kaleidot725.highestpeaks.ui.edit.position

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.data.Position
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.di.service.PositionEditor

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