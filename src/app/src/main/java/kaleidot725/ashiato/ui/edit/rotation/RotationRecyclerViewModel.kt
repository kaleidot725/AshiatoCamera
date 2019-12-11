package kaleidot725.ashiato.ui.edit.rotation

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.data.service.picture.Angle
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.RotationEditor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RotationRecyclerViewModel(
    private val pictureEditor: PictureEditor,
    private val rotationEditor: RotationEditor,
    private val angle: Angle
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _detail: MutableLiveData<String> = MutableLiveData<String>().apply {
        postValue(angle.detail)
    }
    val detail: LiveData<String> get() = _detail

    private val _enabled: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        postValue(isEnabled(angle))
        compositeDisposable.add(
            rotationEditor.enabled.subscribe { postValue(isEnabled(angle)) }
        )
    }
    val enabled: LiveData<Boolean> get() = _enabled

    fun click(v: View) {
        viewModelScope.launch(Dispatchers.Default) {
            rotationEditor.enable(angle)
            pictureEditor.modifyRotation(angle.value)
            pictureEditor.commit()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun isEnabled(angle: Angle): Boolean {
        return (rotationEditor.lastEnabled == angle)
    }
}