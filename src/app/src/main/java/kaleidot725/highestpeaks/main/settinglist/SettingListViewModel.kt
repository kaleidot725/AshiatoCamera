package kaleidot725.highestpeaks.main.settinglist

import androidx.lifecycle.ViewModel;
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.data.Menu

class SettingListViewModel(navigator : MainNavigator, menuList : List<Menu>) : ViewModel() {
    private val _menus : MutableList<MenuViewModel> = mutableListOf()
    val menus : List<MenuViewModel> get() = _menus

    init {
        menuList.forEach {
            this._menus.add(MenuViewModel(navigator, it))
        }
    }
}
