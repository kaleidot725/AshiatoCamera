package kaleidot725.highestpeaks.ui.edit

import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class EditViewModel(val navigator: EditNavigator) : ViewModel() {
    val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            kaleidot725.highestpeaks.R.id.action_format -> {
                navigator.navigateFormatEditor()
                true
            }
            kaleidot725.highestpeaks.R.id.action_style -> {
                navigator.navigateStyleEditor()
                true
            }
            kaleidot725.highestpeaks.R.id.action_color -> {
                navigator.navigateColorEditor()
                true
            }
            kaleidot725.highestpeaks.R.id.action_position -> {
                navigator.navigatePositionEditor()
                true
            }
            kaleidot725.highestpeaks.R.id.action_rotation -> {
                navigator.navigateRotationEditor()
                true
            }
            else -> {
                false
            }
        }
    }
}