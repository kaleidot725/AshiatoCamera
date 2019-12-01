package kaleidot725.ashiato.ui.edit.style

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.StyleRepository
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.StyleEditor
import kotlinx.coroutines.launch

class StyleViewModel(
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val styleRepository: StyleRepository
) : ViewModel() {
    private val _styleRecyclerViewModels: MutableLiveData<List<StyleRecyclerViewModel>> =
        MutableLiveData()
    val styleRecyclerViewModel: LiveData<List<StyleRecyclerViewModel>> get() = _styleRecyclerViewModels

    fun load() {
        viewModelScope.launch {
            val all = styleRepository.all()
            val vms = mutableListOf<StyleRecyclerViewModel>()
            for (style in all) {
                vms.add(StyleRecyclerViewModel(pictureEditor, styleEditor, style))
            }
            _styleRecyclerViewModels.value = vms
        }
    }
}
