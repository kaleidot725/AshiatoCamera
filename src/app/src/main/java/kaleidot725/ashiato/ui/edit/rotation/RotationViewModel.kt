package kaleidot725.ashiato.ui.edit.rotation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.di.repository.AngleRepository
import kaleidot725.ashiato.di.service.picture.PictureEditor
import kaleidot725.ashiato.di.service.picture.RotationEditor
import kotlinx.coroutines.launch

class RotationViewModel(
    private val pictureEditor: PictureEditor,
    private val rotationEditor: RotationEditor,
    private val rotationRepository: AngleRepository
) : ViewModel() {
    private val _rotationRecyclerViewModels: MutableLiveData<List<RotationRecyclerViewModel>> =
        MutableLiveData()
    val rotaionRecyclerViewModels: LiveData<List<RotationRecyclerViewModel>> get() = _rotationRecyclerViewModels

    fun load() {
        viewModelScope.launch {
            val all = rotationRepository.all()
            val vms = mutableListOf<RotationRecyclerViewModel>()
            for (angle in all) {
                vms.add(RotationRecyclerViewModel(pictureEditor, rotationEditor, angle))
            }

            _rotationRecyclerViewModels.value = vms
        }
    }
}
