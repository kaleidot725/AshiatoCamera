package kaleidot725.ashiato.ui.edit.style

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.di.data.Style
import kaleidot725.ashiato.di.service.PictureEditor
import kaleidot725.ashiato.di.service.StyleEditor

class StyleRecyclerViewModel(
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val style : Style
) : ViewModel()
{
    private val _detail : MutableLiveData<String> = MutableLiveData()
    val detail : LiveData<String> get() = _detail

    private val _enabled :  MutableLiveData<Boolean> = MutableLiveData()
    val enabled : LiveData<Boolean> = _enabled

    private val compositeDisposable = CompositeDisposable()

    init {
        _detail.value = style.detail
        _enabled.value = (styleEditor.lastEnabled == style)
        compositeDisposable.add(
            styleEditor.enabled.subscribe { _enabled.postValue(it == style)}
        )
    }

    fun click(v : View) {
        styleEditor.enable(style)
        pictureEditor.modifyTextSize(style.dp)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}