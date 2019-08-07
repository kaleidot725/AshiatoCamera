package kaleidot725.highestpeaks.ui.edit.style

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.repository.FormatRepository
import kaleidot725.highestpeaks.di.repository.StyleRepository
import kaleidot725.highestpeaks.di.service.FormatEditor
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.di.service.StyleEditor
import kaleidot725.highestpeaks.ui.main.history.HistoryViewModel

class StyleViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val styleRepository: StyleRepository
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == StyleViewModel::class.java) {
            return StyleViewModel(pictureEditor, styleEditor, styleRepository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}
