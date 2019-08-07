package kaleidot725.highestpeaks.ui.edit.style

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.data.Style
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.di.service.StyleEditor

class StyleRecyclerViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val style : Style
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == StyleRecyclerViewModel::class.java) {
            return StyleRecyclerViewModel(pictureEditor, styleEditor, style) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}
