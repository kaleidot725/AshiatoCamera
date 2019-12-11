package kaleidot725.ashiato.ui.edit.position

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.Position
import kaleidot725.ashiato.data.service.picture.PositionEditor
import kotlinx.coroutines.launch

class PositionRecyclerViewModel(
    private val pictureEditor: PictureEditor,
    private val positionEditor: PositionEditor,
    private val position: Position
) : ViewModel() {
    private val _detail: MutableLiveData<String> = MutableLiveData()
    val detail: LiveData<String> get() = _detail

    private val _enabled: MutableLiveData<Boolean> = MutableLiveData()
    val enabled: LiveData<Boolean> get() = _enabled

    private val compositeDisposable = CompositeDisposable()

    init {
        _detail.value = position.detail
        _enabled.value = (positionEditor.lastEnabled == position)
        compositeDisposable.add(
            positionEditor.enabled.subscribe { _enabled.postValue((it == position)) }
        )
    }

    fun click(v: View) {
        viewModelScope.launch {
            positionEditor.enable(position)
            pictureEditor.modifyPosition(position.type)
            pictureEditor.commit()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}