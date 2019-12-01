package kaleidot725.ashiato.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.data.service.contact.Developer

class DeveloperViewModel(developer: Developer) : ViewModel() {
    private val _name: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = developer.name
    }
    val name: LiveData<String> get() = _name

    private val _github: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = developer.github
    }
    val github: LiveData<String> get() = _github

    private val _twitter: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = developer.twitter
    }
    val twitter: LiveData<String> get() = _twitter

    private val _email: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = developer.email
    }
    val email: LiveData<String> get() = _email
}
