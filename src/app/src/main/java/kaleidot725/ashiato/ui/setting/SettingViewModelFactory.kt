package kaleidot725.ashiato.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.persistence.PersistenceSetting

class SettingViewModelFactory(persistenceSetting : PersistenceSetting) : ViewModelProvider.Factory {
    private val persistenceSetting : PersistenceSetting = persistenceSetting

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SettingViewModel::class.java) {
            return SettingViewModel(persistenceSetting) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}