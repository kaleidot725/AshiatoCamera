package kaleidot725.ashiato.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.service.location.PermanentLocationSetting

class SettingViewModelFactory(persistenceSetting: PermanentLocationSetting) : ViewModelProvider.Factory {
    private val persistenceSetting: PermanentLocationSetting = persistenceSetting

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SettingViewModel::class.java) {
            return SettingViewModel(persistenceSetting) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}