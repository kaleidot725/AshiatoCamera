package kaleidot725.highestpeaks.contact

import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.main.settinglist.MenuViewModel
import kaleidot725.highestpeaks.model.repository.Developer

class ContactViewModel(developerList : List<Developer>) : ViewModel() {
    private val _developers : MutableList<DeveloperViewModel> = mutableListOf()
    val developers : List<DeveloperViewModel> get() = _developers

    init {
        developerList.forEach {
            this._developers.add(DeveloperViewModel(it))
        }
    }
}
