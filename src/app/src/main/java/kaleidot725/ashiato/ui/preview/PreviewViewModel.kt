package kaleidot725.ashiato.ui.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.michetimer.model.repository.PictureRepository

class PreviewViewModel(val repository: PictureRepository) : ViewModel() {

    private val _currentPage : MutableLiveData<Int> = MutableLiveData()
    val currentPage : LiveData<Int> get() = _currentPage

    private val _pageCount : MutableLiveData<Int> = MutableLiveData()
    val pageCount : LiveData<Int> get() = _pageCount

    init {
        _currentPage.value = repository.all().indexOf(repository.previewed)
        _pageCount.value = repository.all().count()
    }
}