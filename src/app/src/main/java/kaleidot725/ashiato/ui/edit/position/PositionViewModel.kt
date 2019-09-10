package kaleidot725.ashiato.ui.edit.position

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import kaleidot725.ashiato.di.repository.PositionRepository
import kaleidot725.ashiato.di.service.picture.PictureEditor
import kaleidot725.ashiato.di.service.picture.PositionEditor

class PositionViewModel(
    private val pictureEditor: PictureEditor,
    private val positionEditor: PositionEditor,
    private val positionRepository: PositionRepository
) : ViewModel() {

    private val _positionRecyclerViewModels: MutableLiveData<List<PositionRecyclerViewModel>> = MutableLiveData()
    val positionRecyclerViewModels: LiveData<List<PositionRecyclerViewModel>> get() = _positionRecyclerViewModels

    init {
        val all = positionRepository.all()
        val vms = mutableListOf<PositionRecyclerViewModel>()
        for (position in all) {
            vms.add(PositionRecyclerViewModel(pictureEditor, positionEditor, position))
        }

        _positionRecyclerViewModels.value = vms
    }
}