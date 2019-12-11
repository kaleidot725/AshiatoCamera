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
import kotlinx.coroutines.launch

class RotationRecyclerViewModel(
    private val pictureEditor: PictureEditor,
    private val rotationEditor: RotationEditor,
    private val angle: Angle
) : ViewModel() {
    private val _detail: MutableLiveData<String> = MutableLiveData()
    val detail: LiveData<String> get() = _detail

    private val _enabled: MutableLiveData<Boolean> = MutableLiveData()
    val enabled: LiveData<Boolean> get() = _enabled

    private val compositeDisposable = CompositeDisposable()

    init {
        _detail.value = angle.detail
        _enabled.value = (rotationEditor.lastEnabled == angle)
        compositeDisposable.add(
            rotationEditor.enabled.subscribe { _enabled.postValue(it == angle) }
        )
    }

    fun click(v: View) {
        viewModelScope.launch {
            rotationEditor.enable(angle)
            pictureEditor.modifyRotation(angle.value)
            pictureEditor.commit()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}