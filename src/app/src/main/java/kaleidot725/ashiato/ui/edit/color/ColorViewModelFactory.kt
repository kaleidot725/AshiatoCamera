package kaleidot725.ashiato.ui.edit.color

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.ColorRepository
import kaleidot725.ashiato.di.service.picture.ColorEditor
import kaleidot725.ashiato.di.service.picture.PictureEditor

class ColorViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val colorEditor: ColorEditor,
    private val colorRepository: ColorRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ColorViewModel::class.java) {
            return ColorViewModel(pictureEditor, colorEditor, colorRepository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}