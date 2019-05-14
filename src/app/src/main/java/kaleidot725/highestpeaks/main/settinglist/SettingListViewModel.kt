package kaleidot725.highestpeaks.main.settinglist

import androidx.lifecycle.ViewModel;
import kaleidot725.highestpeaks.MyApplicationNavigator
import kaleidot725.highestpeaks.model.data.Menu

class SettingListViewModel(navigator : MyApplicationNavigator, menuList : List<Menu>) : ViewModel() {
    private val _menus : MutableList<MenuViewModel> = mutableListOf()
    val menus : List<MenuViewModel> get() = _menus

    init {
        menuList.forEach {
            this._menus.add(MenuViewModel(navigator, it))
        }
    }
}
