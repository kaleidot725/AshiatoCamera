package kaleidot725.ashiato.ui.edit.color

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.ashiato.data.service.picture.Color
import kaleidot725.ashiato.data.service.picture.ColorEditor
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ColorRecyclerViewModel(
    private val pictureEditor: PictureEditor,
    private val colorEditor: ColorEditor,
    private val color: Color
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _detail: MutableLiveData<String> = MutableLiveData<String>().apply {
        postValue(color.detail)
    }
    val detail: LiveData<String> get() = _detail

    private val _txtColor: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        postValue(selectBackgroundColor(color))
    }
    val txtColor: LiveData<Int> get() = _txtColor

    private val _enabled: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        postValue(isSelectedColor(color))
        compositeDisposable.add(
            colorEditor.enabled.subscribe {
                postValue(isSelectedColor(color))
            }
        )
    }
    val enabled: LiveData<Boolean> get() = _enabled

    fun click(v: View) {
        viewModelScope.launch(Dispatchers.Default) {
            colorEditor.enable(color)
            pictureEditor.modifyColor(color.value)
            pictureEditor.commit()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun selectBackgroundColor(thisColor: Color): Int {
        return if (thisColor.value == android.graphics.Color.WHITE) {
            android.graphics.Color.BLACK
        } else {
            thisColor.value
        }
    }

    private fun isSelectedColor(thisColor: Color): Boolean {
        return (colorEditor.lastEnabled == thisColor)
    }
}