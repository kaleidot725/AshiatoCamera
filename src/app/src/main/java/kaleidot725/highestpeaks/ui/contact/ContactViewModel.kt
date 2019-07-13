package kaleidot725.highestpeaks.ui.contact

import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Developer

class ContactViewModel(developerList : List<Developer>) : ViewModel() {
    private val _developers : MutableList<DeveloperViewModel> = mutableListOf()
    val developers : List<DeveloperViewModel> get() = _developers

    init {
        developerList.forEach {
            this._developers.add(DeveloperViewModel(it))
        }
    }
}
