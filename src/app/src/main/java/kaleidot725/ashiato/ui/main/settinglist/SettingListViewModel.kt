package kaleidot725.ashiato.ui.main.settinglist

import android.content.Context
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.data.service.contact.Menu
import kaleidot725.ashiato.ui.main.MainNavigator

class SettingListViewModel(
    val context: Context,
    val navigator: MainNavigator,
    val menuList: List<Menu>
) : ViewModel() {
    private val _menus: MutableList<MenuViewModel> = mutableListOf()
    val menus: List<MenuViewModel> get() = _menus

    init {
        menuList.forEach {
            this._menus.add(MenuViewModel(context, navigator, it))
        }
    }
}
