package kaleidot725.ashiato.ui.edit.format

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.di.data.Format
import kaleidot725.ashiato.di.service.picture.FormatEditor
import kaleidot725.ashiato.di.service.picture.PictureEditor

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
        val enable = !(_enabled.value ?: true)
        _enabled.postValue(enable)
        formatEditor.enable(format.type, enable)
        pictureEditor.modifyText(formatEditor.create())
    }
}