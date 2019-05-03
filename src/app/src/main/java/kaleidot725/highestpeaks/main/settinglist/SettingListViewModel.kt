package kaleidot725.highestpeaks.main.settinglist

import android.content.DialogInterface
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.highestpeaks.MyApplicationNavigator
import kaleidot725.highestpeaks.model.repository.Menu

class SettingListViewModel(navigator : MyApplicationNavigator, menuList : List<Menu>) : ViewModel() {
    private val _menus : MutableList<MenuViewModel> = mutableListOf()
    val menus : List<MenuViewModel> get() = _menus

    init {
        menuList.forEach {
            this._menus.add(MenuViewModel(navigator, it))
        }
    }
}
