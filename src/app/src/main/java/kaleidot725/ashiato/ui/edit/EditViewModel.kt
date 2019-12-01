package kaleidot725.ashiato.ui.edit

import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class EditViewModel : ViewModel() {
    var navigator: EditNavigator? = null

    val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        it.isChecked = true

        when (it.itemId) {
            kaleidot725.ashiato.R.id.action_format -> {
                navigator?.navigateFormatEditor()
                true
            }
            kaleidot725.ashiato.R.id.action_style -> {
                navigator?.navigateStyleEditor()
                true
            }
            kaleidot725.ashiato.R.id.action_color -> {
                navigator?.navigateColorEditor()
                true
            }
            kaleidot725.ashiato.R.id.action_position -> {
                navigator?.navigatePositionEditor()
                true
            }
            kaleidot725.ashiato.R.id.action_rotation -> {
                navigator?.navigateRotationEditor()
                true
            }
            else -> {
                false
            }
        }
    }
}