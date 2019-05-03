package kaleidot725.highestpeaks.main.settinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.MyApplicationNavigator
import kaleidot725.highestpeaks.model.repository.Menu

class SettingListViewModelFactory(navigator : MyApplicationNavigator, menus : List<Menu>) : ViewModelProvider.Factory {
    private val navigator : MyApplicationNavigator = navigator
    private val menus : List<Menu> = menus

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SettingListViewModel::class.java) {
            return SettingListViewModel(navigator, menus) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}