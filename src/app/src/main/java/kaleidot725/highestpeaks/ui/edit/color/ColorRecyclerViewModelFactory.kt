package kaleidot725.highestpeaks.ui.edit.color

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.data.Color
import kaleidot725.highestpeaks.di.service.ColorEditor
import kaleidot725.highestpeaks.di.service.PictureEditor
import java.lang.IllegalArgumentException

class ColorRecyclerViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val colorEditor: ColorEditor,
    private val color: Color
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ColorRecyclerViewModel::class.java) {
            return ColorRecyclerViewModel(pictureEditor, colorEditor, color) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}