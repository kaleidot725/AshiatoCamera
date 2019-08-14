package kaleidot725.ashiato.ui.edit.format

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.FormatRepository
import kaleidot725.ashiato.di.service.FormatEditor
import kaleidot725.ashiato.di.service.PictureEditor

class FormatViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val formatEditor : FormatEditor,
    private val formatRepository : FormatRepository
) : ViewModelProvider.Factory
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == FormatViewModel::class.java) {
            return FormatViewModel(pictureEditor, formatEditor, formatRepository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}
