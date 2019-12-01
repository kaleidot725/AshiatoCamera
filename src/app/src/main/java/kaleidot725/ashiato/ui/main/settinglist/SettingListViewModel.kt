package kaleidot725.ashiato.ui.main.settinglist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.MenuRepository
import kotlinx.coroutines.launch

class SettingListViewModel(
    val context: Context,
    val menuRepository: MenuRepository
) : ViewModel() {
    private val _menus: MutableLiveData<List<MenuViewModel>> = MutableLiveData()
    val menus: LiveData<List<MenuViewModel>> get() = _menus

    var navigator: SettingListNaviagator? = null

    fun load() {
        viewModelScope.launch {
            val vms = menuRepository.all().map { MenuViewModel(context, navigator, it) }
            _menus.postValue(vms)
        }
    }
}
