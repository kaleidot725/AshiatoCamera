package kaleidot725.ashiato.ui.edit.style

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.StyleRepository
import kaleidot725.ashiato.di.service.picture.PictureEditor
import kaleidot725.ashiato.di.service.picture.StyleEditor

class StyleViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val styleRepository: StyleRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == StyleViewModel::class.java) {
            return StyleViewModel(pictureEditor, styleEditor, styleRepository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}
