package kaleidot725.highestpeaks.main.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel(menu : Menu) : ViewModel() {
    private val _icon : MutableLiveData<Int> = MutableLiveData<Int>()
    val icon : LiveData<Int> get() = _icon

    private val _title : MutableLiveData<String> = MutableLiveData<String>()
    val title : LiveData<String> get() = _title

    init {
        _icon.value = menu.icon
        _title.value = menu.title
    }
}