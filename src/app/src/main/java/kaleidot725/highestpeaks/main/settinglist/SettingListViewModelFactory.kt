package kaleidot725.highestpeaks.main.settinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.model.repository.Menu

class SettingListViewModelFactory(menus : List<Menu>) : ViewModelProvider.Factory {
    private val menus : List<Menu> = menus

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SettingListViewModel::class.java) {
            return SettingListViewModel(menus) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}