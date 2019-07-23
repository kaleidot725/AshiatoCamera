package kaleidot725.highestpeaks.ui.preview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.Holder
import kaleidot725.michetimer.model.repository.PictureRepository

class PreviewViewModel(val repository: PictureRepository, val selected : Holder<Picture>) : ViewModel() {

    private val _currentPage : MutableLiveData<Int> = MutableLiveData()
    val currentPage : LiveData<Int> get() = _currentPage

    private val _pageCount : MutableLiveData<Int> = MutableLiveData()
    val pageCount : LiveData<Int> get() = _pageCount

    init {
        _currentPage.value = repository.all().indexOf(selected.lastedValue)
        _pageCount.value = repository.all().count()
    }
}