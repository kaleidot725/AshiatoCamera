package kaleidot725.highestpeaks.main.settinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.data.Menu

class MenuViewModelFactory(navigaor : MainNavigator, menu : Menu) : ViewModelProvider.Factory {
    private val navigaor : MainNavigator = navigaor
    private val menu : Menu = menu

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MenuViewModel::class.java) {
            return MenuViewModel(navigaor, menu) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}