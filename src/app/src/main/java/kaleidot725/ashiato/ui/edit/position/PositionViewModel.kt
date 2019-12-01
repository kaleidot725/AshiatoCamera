package kaleidot725.ashiato.ui.edit.position

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.PositionRepository
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.PositionEditor
import kotlinx.coroutines.launch

class PositionViewModel(
    private val pictureEditor: PictureEditor,
    private val positionEditor: PositionEditor,
    private val positionRepository: PositionRepository
) : ViewModel() {

    private val _positionRecyclerViewModels: MutableLiveData<List<PositionRecyclerViewModel>> =
        MutableLiveData()
    val positionRecyclerViewModels: LiveData<List<PositionRecyclerViewModel>> get() = _positionRecyclerViewModels

    fun load() {
        viewModelScope.launch {
            val all = positionRepository.all()
            val vms = mutableListOf<PositionRecyclerViewModel>()
            for (position in all) {
                vms.add(PositionRecyclerViewModel(pictureEditor, positionEditor, position))
            }

            _positionRecyclerViewModels.value = vms
        }
    }
}