package kaleidot725.ashiato.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import kaleidot725.ashiato.data.repository.EditType
import kaleidot725.ashiato.data.repository.PictureRepository

class MainViewModel(
    private val pictureRepository: PictureRepository
) : ViewModel(), Disposable {

    enum class NavEvent {
        Camera,
        Folder,
        Home,
        History,
        SettingList
    }

    private var disposed: Boolean = false

    private val _navigationEvent: MutableLiveData<NavEvent> = MutableLiveData()
    val navigationEvent: LiveData<NavEvent> = _navigationEvent

    val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                kaleidot725.ashiato.R.id.action_home -> {
                    _navigationEvent.postValue(NavEvent.Home)
                    true
                }
                kaleidot725.ashiato.R.id.action_history -> {
                    _navigationEvent.postValue(NavEvent.History)
                    true
                }
                kaleidot725.ashiato.R.id.action_setting -> {
                    _navigationEvent.postValue(NavEvent.SettingList)
                    true
                }
                else -> {
                    false
                }
            }
        }

    fun takePhoto(view: View) {
        pictureRepository.edit(EditType.TOOK, pictureRepository.newPicture())
        _navigationEvent.postValue(NavEvent.Camera)
    }

    fun selectPhoto(view: View) {
        pictureRepository.edit(EditType.FOLDER, pictureRepository.newPicture())
        _navigationEvent.postValue(NavEvent.Folder)
    }

    override fun dispose() {
        if (disposed) {
            throw IllegalStateException("already disposed")
        }

        disposed = true
    }

    override fun isDisposed(): Boolean {
        return disposed
    }
}