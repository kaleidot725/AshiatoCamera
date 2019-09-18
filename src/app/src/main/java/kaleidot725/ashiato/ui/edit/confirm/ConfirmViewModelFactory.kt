package kaleidot725.ashiato.ui.edit.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.*
import kaleidot725.ashiato.di.service.picture.*
import kaleidot725.ashiato.ui.edit.EditNavigator

class ConfirmViewModelFactory(
    val navigator: EditNavigator,
    val pictureEditor: PictureEditor,
    val formatEditor: FormatEditor,
    val colorEditor : ColorEditor,
    val styleEditor: StyleEditor,
    val positionEditor: PositionEditor,
    val rotationEditor: RotationEditor,
    val pictureSetting : PermanentPictureSetting,
    val dateTimeRepository: DateTimeRepository,
    val locationRepository: LocationRepository,
    val formatRepository: FormatRepository,
    val pictureRepository: PictureRepository,
    val angleRepository: AngleRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ConfirmViewModel::class.java) {
            return ConfirmViewModel(
                navigator,
                pictureEditor,
                formatEditor,
                colorEditor,
                styleEditor,
                positionEditor,
                rotationEditor,
                pictureSetting,
                dateTimeRepository,
                locationRepository,
                formatRepository,
                pictureRepository,
                angleRepository
            ) as T
        }

        throw Exception("have created unknown class type")
    }
}