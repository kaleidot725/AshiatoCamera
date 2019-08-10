package kaleidot725.highestpeaks.ui.edit.color

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.repository.ColorRepository
import kaleidot725.highestpeaks.di.service.ColorEditor
import kaleidot725.highestpeaks.di.service.PictureEditor
import java.lang.IllegalArgumentException

class ColorViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val colorEditor: ColorEditor,
    private val colorRepository: ColorRepository
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ColorViewModel::class.java) {
            return ColorViewModel(pictureEditor, colorEditor, colorRepository) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}