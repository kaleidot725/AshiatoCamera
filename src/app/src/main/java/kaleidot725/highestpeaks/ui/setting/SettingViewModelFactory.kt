package kaleidot725.highestpeaks.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.repository.PersistenceSetting

class SettingViewModelFactory(persistenceSetting : PersistenceSetting) : ViewModelProvider.Factory {
    private val persistenceSetting : PersistenceSetting = persistenceSetting

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SettingViewModel::class.java) {
            return SettingViewModel(persistenceSetting) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}