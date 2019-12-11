package kaleidot725.ashiato.ui.edit.format

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.service.picture.Format
import kaleidot725.ashiato.data.service.picture.FormatEditor
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormatRecyclerViewModel(
    private val pictureEditor: PictureEditor,
    private val formatEditor: FormatEditor,
    private val format: Format
) : ViewModel() {
    private val _detail: MutableLiveData<String> = MutableLiveData<String>().apply {
        postValue(format.detail)
    }
    val detail: LiveData<String> get() = _detail

    private val _enabled: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        postValue(formatEditor.enabled(format.type))
    }
    val enabled: LiveData<Boolean> get() = _enabled

    fun click(v: View) {
        viewModelScope.launch(Dispatchers.Default) {
            val enable = !(_enabled.value ?: true)
            _enabled.postValue(enable)
            formatEditor.enable(format.type, enable)
            pictureEditor.modifyText(formatEditor.create())
            pictureEditor.commit()
        }
    }
}