package kaleidot725.highestpeaks.main.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.main.history.HistoryViewModel
import kaleidot725.michetimer.model.repository.PictureRepository

class SettingViewModelFactory(menus : List<Menu>) : ViewModelProvider.Factory {
    private val menus : List<Menu> = menus

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SettingViewModel::class.java) {
            return SettingViewModel(menus) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}