package kaleidot725.highestpeaks.ui.edit.color

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import kaleidot725.highestpeaks.di.repository.ColorRepository
import kaleidot725.highestpeaks.di.service.ColorEditor
import kaleidot725.highestpeaks.di.service.PictureEditor

class ColorViewModel(
    private val pictureEditor: PictureEditor,
    private val colorEditor: ColorEditor,
    private val colorRepository: ColorRepository
) : ViewModel() {
    private val _colorRecyclerViewModels : MutableLiveData<List<ColorRecyclerViewModel>> = MutableLiveData()
    val colorRecyclerViewModels : LiveData<List<ColorRecyclerViewModel>> get() = _colorRecyclerViewModels

    init {
        val all = colorRepository.all()
        val vms = mutableListOf<ColorRecyclerViewModel>()
        for(color in all) {
            vms.add(ColorRecyclerViewModel(pictureEditor, colorEditor, color))
        }

        _colorRecyclerViewModels.value = vms
    }
}
