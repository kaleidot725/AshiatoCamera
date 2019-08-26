package kaleidot725.ashiato.ui.edit.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.AngleRepository
import kaleidot725.ashiato.di.repository.DateTimeRepository
import kaleidot725.ashiato.di.repository.LocationRepository
import kaleidot725.ashiato.di.repository.PictureRepository
import kaleidot725.ashiato.di.service.FormatEditor
import kaleidot725.ashiato.di.service.PictureEditor
import kaleidot725.ashiato.di.service.RotationEditor
import kaleidot725.ashiato.ui.edit.EditNavigator

class ConfirmViewModelFactory(
    val navigator: EditNavigator,
    val dateTimeRepository: DateTimeRepository,
    val locationRepository: LocationRepository,
    val pictureRepository: PictureRepository,
    val angleRepository: AngleRepository,
    val formatEditor: FormatEditor,
    val rotationEditor: RotationEditor,
    val bitmapEditor: PictureEditor
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ConfirmViewModel::class.java) {
            return ConfirmViewModel(
                navigator,
                dateTimeRepository,
                locationRepository,
                pictureRepository,
                angleRepository,
                formatEditor,
                rotationEditor,
                bitmapEditor
            ) as T
        }

        throw Exception("have created unknown class type")
    }
}