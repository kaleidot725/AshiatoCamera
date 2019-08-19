package kaleidot725.ashiato.ui.main.settinglist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.data.Menu
import kaleidot725.ashiato.ui.main.MainNavigator

class SettingListViewModelFactory(
    val context: Context,
    val navigator: MainNavigator,
    val menus: List<Menu>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SettingListViewModel::class.java) {
            return SettingListViewModel(context, navigator, menus) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}