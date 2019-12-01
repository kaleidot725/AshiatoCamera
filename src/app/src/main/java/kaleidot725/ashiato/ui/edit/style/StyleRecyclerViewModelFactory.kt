package kaleidot725.ashiato.ui.edit.style

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.data.service.picture.Style
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.StyleEditor

class StyleRecyclerViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val style: Style
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == StyleRecyclerViewModel::class.java) {
            return StyleRecyclerViewModel(pictureEditor, styleEditor, style) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}
