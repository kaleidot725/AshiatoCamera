package kaleidot725.ashiato.ui.edit.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.repository.*
import kaleidot725.ashiato.di.service.picture.*
import kaleidot725.ashiato.ui.edit.EditNavigator

class ConfirmViewModelFactory(
    private val navigator: EditNavigator,
    private val pictureEditor: PictureEditor,
    private val formatEditor: FormatEditor,
    private val colorEditor : ColorEditor,
    private val styleEditor: StyleEditor,
    private val positionEditor: PositionEditor,
    private val rotationEditor: RotationEditor,
    private val pictureSetting : PermanentPictureSetting,
    private val dateTimeRepository: DateTimeRepository,
    private val locationRepository: LocationRepository,
    private val formatRepository: FormatRepository,
    private val pictureRepository: PictureRepository,
    private val angleRepository: AngleRepository
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