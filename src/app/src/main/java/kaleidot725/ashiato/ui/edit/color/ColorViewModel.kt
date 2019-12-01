package kaleidot725.ashiato.ui.edit.color

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.ColorRepository
import kaleidot725.ashiato.data.service.picture.ColorEditor
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kotlinx.coroutines.launch

class ColorViewModel(
    private val pictureEditor: PictureEditor,
    private val colorEditor: ColorEditor,
    private val colorRepository: ColorRepository
) : ViewModel() {
    private val _colorRecyclerViewModels: MutableLiveData<List<ColorRecyclerViewModel>> =
        MutableLiveData()
    val colorRecyclerViewModels: LiveData<List<ColorRecyclerViewModel>> get() = _colorRecyclerViewModels

    fun load() {
        viewModelScope.launch {
            val all = colorRepository.all()
            val vms = mutableListOf<ColorRecyclerViewModel>()
            for (color in all) {
                vms.add(ColorRecyclerViewModel(pictureEditor, colorEditor, color))
            }

            _colorRecyclerViewModels.value = vms
        }
    }

}
