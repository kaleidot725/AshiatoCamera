package kaleidot725.highestpeaks.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Developer

class DeveloperViewModel(developer : Developer) : ViewModel() {
    private val _name : MutableLiveData<String> = MutableLiveData()
    val name : LiveData<String> get() = _name

    private val _github : MutableLiveData<String> = MutableLiveData()
    val github : LiveData<String> get() = _github

    private val _twitter : MutableLiveData<String> = MutableLiveData()
    val twitter : LiveData<String> get() = _twitter

    private val _email : MutableLiveData<String> = MutableLiveData()
    val email : LiveData<String> get() = _email

    init {
        _name.value = developer.name
        _github.value = developer.github
        _twitter.value = developer.twitter
        _email.value = developer.email
    }
}
