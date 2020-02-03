package kaleidot725.ashiato.ui.main.settinglist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.data.service.contact.Menu

class MenuViewModel(
    val menu: Menu,
    val clickEvent: (m: Menu) -> Unit
) : ViewModel() {

    private val _icon: MutableLiveData<Int> = MutableLiveData<Int>()
    val icon: LiveData<Int> get() = _icon

    private val _title: MutableLiveData<String> = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    init {
        _icon.value = menu.icon
        _title.value = menu.title
    }

    fun click(view: View) {
        clickEvent(this.menu)
    }
}