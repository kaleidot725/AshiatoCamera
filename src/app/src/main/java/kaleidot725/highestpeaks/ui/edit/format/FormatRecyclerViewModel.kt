package kaleidot725.highestpeaks.ui.edit.format

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.di.data.Format
import kaleidot725.highestpeaks.di.service.FormatEditor

class FormatRecyclerViewModel(
    private val formatEditor: FormatEditor,
    private val format : Format
) : ViewModel()
{
    private val _detail : MutableLiveData<String> = MutableLiveData()
    val detail : LiveData<String> get() = _detail

    private val _enabled : MutableLiveData<Boolean> = MutableLiveData()
    val enabled : LiveData<Boolean> = _enabled

    init {
        _detail.value = format.detail
        _enabled.value = formatEditor.enabled(format.type)
    }

    fun click(v : View) {
        val enable = !(_enabled.value ?: true)
        _enabled.postValue(enable)
        formatEditor.enable(format.type, enable)
    }
}