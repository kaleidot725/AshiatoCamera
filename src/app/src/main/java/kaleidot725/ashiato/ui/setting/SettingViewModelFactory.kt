package kaleidot725.ashiato.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.data.service.location.PermanentLocationSetting

class SettingViewModelFactory(private val persistenceSetting: PermanentLocationSetting) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SettingViewModel::class.java) {
            return SettingViewModel(persistenceSetting) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}