package kaleidot725.highestpeaks.ui.edit.format

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import kaleidot725.highestpeaks.di.repository.FormatRepository
import kaleidot725.highestpeaks.di.service.FormatEditor
import kaleidot725.highestpeaks.di.service.PictureEditor

class FormatViewModel(
    private val pictureEditor: PictureEditor,
    private val formatEditor: FormatEditor,
    private val formatRepository: FormatRepository
) : ViewModel()
{
    private val _formatRecyclerViewModels : MutableLiveData<List<FormatRecyclerViewModel>> = MutableLiveData()
    val formatRecyclerViewModels : LiveData<List<FormatRecyclerViewModel>> get() = _formatRecyclerViewModels

    init {
        val all = formatRepository.all()
        val vms = mutableListOf<FormatRecyclerViewModel>()
        for (format in all) {
            vms.add(FormatRecyclerViewModel(pictureEditor, formatEditor, format))
        }
        _formatRecyclerViewModels.value = vms
    }
}
