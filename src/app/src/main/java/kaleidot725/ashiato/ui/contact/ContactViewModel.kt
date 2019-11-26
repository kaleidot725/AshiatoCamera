package kaleidot725.ashiato.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.di.service.contact.Developer

class ContactViewModel(developerList: List<Developer>) : ViewModel() {
    private val _developers: MutableLiveData<List<DeveloperViewModel>> =
        MutableLiveData<List<DeveloperViewModel>>().apply {
            value = developerList.map { DeveloperViewModel(it) }
        }
    val developers: LiveData<List<DeveloperViewModel>> get() = _developers
}
