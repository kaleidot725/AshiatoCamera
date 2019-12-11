package kaleidot725.ashiato.ui.edit.format

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.service.picture.Format
import kaleidot725.ashiato.data.service.picture.FormatEditor
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kotlinx.coroutines.launch

class FormatRecyclerViewModel(
    private val pictureEditor: PictureEditor,
    private val formatEditor: FormatEditor,
    private val format: Format
) : ViewModel() {
    private val _detail: MutableLiveData<String> = MutableLiveData()
    val detail: LiveData<String> get() = _detail

    private val _enabled: MutableLiveData<Boolean> = MutableLiveData()
    val enabled: LiveData<Boolean> get() = _enabled

    init {
        _detail.value = format.detail
        _enabled.value = formatEditor.enabled(format.type)
    }

    fun click(v: View) {
        viewModelScope.launch {
            val enable = !(_enabled.value ?: true)
            _enabled.postValue(enable)
            formatEditor.enable(format.type, enable)
            pictureEditor.modifyText(formatEditor.create())
            pictureEditor.commit()
        }
    }
}