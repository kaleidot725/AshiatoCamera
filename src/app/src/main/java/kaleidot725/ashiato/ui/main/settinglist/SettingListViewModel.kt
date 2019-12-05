package kaleidot725.ashiato.ui.main.settinglist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.R
import kaleidot725.ashiato.data.repository.MenuRepository
import kotlinx.coroutines.launch

class SettingListViewModel(
    val context: Context,
    val menuRepository: MenuRepository
) : ViewModel() {
    enum class NavEvent {
        Setting,
        License,
        Contact,
        Privacy,
    }

    private val _menus: MutableLiveData<List<MenuViewModel>> = MutableLiveData()
    val menus: LiveData<List<MenuViewModel>> get() = _menus

    private val _event: MutableLiveData<NavEvent> = MutableLiveData()
    val event: LiveData<NavEvent> = _event

    fun load() {
        viewModelScope.launch {
            val vms = menuRepository.all().map {
                MenuViewModel(context, it) { menu ->
                    when (menu.title) {
                        context.getString(R.string.menu_setting) -> _event.postValue(NavEvent.Setting)
                        context.getString(R.string.menu_license) -> _event.postValue(NavEvent.License)
                        context.getString(R.string.menu_contanct) -> _event.postValue(NavEvent.Contact)
                        context.getString(R.string.menu_privacy) -> _event.postValue(NavEvent.Privacy)
                    }
                }
            }
            _menus.postValue(vms)
        }
    }
}
