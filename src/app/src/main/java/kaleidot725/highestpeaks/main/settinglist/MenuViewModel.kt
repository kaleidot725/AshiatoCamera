package kaleidot725.highestpeaks.main.settinglist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.data.Menu

class MenuViewModel(navigator : MainNavigator,  menu : Menu) : ViewModel() {
    private val navigator : MainNavigator = navigator
    private val menu : Menu = menu

    private val _icon : MutableLiveData<Int> = MutableLiveData<Int>()
    val icon : LiveData<Int> get() = _icon

    private val _title : MutableLiveData<String> = MutableLiveData<String>()
    val title : LiveData<String> get() = _title

    init {
        _icon.value = menu.icon
        _title.value = menu.title
    }

    fun click(view : View) {
        when(menu.title) {
            "Setting" -> navigator.navigateSetting()
            "License" -> navigator.navigateLicense()
            "Contact" -> navigator.navigateContact()
        }
    }
}