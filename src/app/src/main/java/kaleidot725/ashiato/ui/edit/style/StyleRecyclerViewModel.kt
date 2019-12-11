package kaleidot725.ashiato.ui.edit.style

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.Style
import kaleidot725.ashiato.data.service.picture.StyleEditor
import kotlinx.coroutines.launch

class StyleRecyclerViewModel(
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val style: Style
) : ViewModel() {
    private val _detail: MutableLiveData<String> = MutableLiveData()
    val detail: LiveData<String> get() = _detail

    private val _enabled: MutableLiveData<Boolean> = MutableLiveData()
    val enabled: LiveData<Boolean> = _enabled

    private val compositeDisposable = CompositeDisposable()

    init {
        _detail.value = style.detail
        _enabled.value = (styleEditor.lastEnabled == style)
        compositeDisposable.add(
            styleEditor.enabled.subscribe { _enabled.postValue(it == style) }
        )
    }

    fun click(v: View) {
        viewModelScope.launch {
            styleEditor.enable(style)
            pictureEditor.modifyTextSize(style.dp)
            pictureEditor.commit()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}