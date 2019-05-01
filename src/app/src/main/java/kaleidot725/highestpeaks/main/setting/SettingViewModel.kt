package kaleidot725.highestpeaks.main.setting

import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders

class SettingViewModel(menuList : List<Menu>) : ViewModel() {
    private val _menus : MutableList<MenuViewModel> = mutableListOf()
    val menus : List<MenuViewModel> get() = _menus

    init {
        menuList.forEach {
            this._menus.add(MenuViewModel(it))
        }
    }
}
