package kaleidot725.daycamera.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaleidot725.daycamera.data.repository.MenuRepository
import kaleidot725.daycamera.data.service.contact.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingViewModel(private val menuRepository: MenuRepository) : ViewModel() {
    private val _menuList: MutableLiveData<List<Menu>> = MutableLiveData()
    val menuList: LiveData<List<Menu>> = _menuList

    fun fetchMenu() {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    menuRepository.all()
                }
            }.onSuccess {
                _menuList.value = it
            }
        }
    }
}