package kaleidot725.ashiato.ui.edit

import android.view.MenuItem
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kaleidot725.ashiato.data.repository.*
import kaleidot725.ashiato.data.service.picture.*
import kotlinx.coroutines.launch

class EditViewModel(
    private val pictureEditor: PictureEditor,
    private val formatEditor: FormatEditor,
    private val colorEditor: ColorEditor,
    private val styleEditor: StyleEditor,
    private val positionEditor: PositionEditor,
    private val rotationEditor: RotationEditor,
    private val pictureSetting: PermanentPictureSetting,
    private val dateTimeRepository: DateTimeRepository,
    private val locationRepository: LocationRepository,
    private val formatRepository: FormatRepository,
    private val pictureRepository: PictureRepository,
    private val angleRepository: AngleRepository
) : ViewModel() {
    enum class NavEvent {
        Format,
        Style,
        Color,
        Position,
        Rotation,
        Exit,
    }

    private val _event: MutableLiveData<NavEvent> = MutableLiveData()
    val event: LiveData<NavEvent> = _event

    val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        it.isChecked = true
        _event.postValue(resolveEvent(it))
        true
    }

    fun save(view: View) {
        viewModelScope.launch {
            val formats = formatRepository.all().filter { formatEditor.enabled(it.type) }
            val setting = PictureSetting(
                colorEditor.lastEnabled,
                styleEditor.lastEnabled,
                formats,
                positionEditor.lastEnabled,
                rotationEditor.lastEnabled
            )
            pictureSetting.save(setting)

            pictureEditor.end()
            _event.postValue(NavEvent.Exit)
        }
    }

    fun cancel(view: View) {
        viewModelScope.launch {
            val formats = formatRepository.all().filter { formatEditor.enabled(it.type) }
            val setting = PictureSetting(
                colorEditor.lastEnabled,
                styleEditor.lastEnabled,
                formats,
                positionEditor.lastEnabled,
                rotationEditor.lastEnabled
            )
            pictureSetting.save(setting)

            pictureEditor.cancel()
            _event.postValue(NavEvent.Exit)
        }
    }

    private fun resolveEvent(i: MenuItem): NavEvent? {
        return when (i.itemId) {
            kaleidot725.ashiato.R.id.action_format -> NavEvent.Format
            kaleidot725.ashiato.R.id.action_style -> NavEvent.Style
            kaleidot725.ashiato.R.id.action_color -> NavEvent.Color
            kaleidot725.ashiato.R.id.action_position -> NavEvent.Position
            kaleidot725.ashiato.R.id.action_rotation -> NavEvent.Rotation
            else -> null
        }
    }
}