package kaleidot725.highestpeaks.ui.edit.style

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.highestpeaks.di.data.Style
import kaleidot725.highestpeaks.di.repository.StyleRepository
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.di.service.StyleEditor

class StyleViewModel (
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val styleRepository: StyleRepository
) : ViewModel()
{
    private val _styleRecyclerViewModels : MutableLiveData<List<StyleRecyclerViewModel>> = MutableLiveData()
    val styleRecyclerViewModel : LiveData<List<StyleRecyclerViewModel>> get() = _styleRecyclerViewModels

    init {
        val all = styleRepository.all()
        val vms = mutableListOf<StyleRecyclerViewModel>()
        for (style in all) {
            vms.add(StyleRecyclerViewModel(pictureEditor, styleEditor, style))
        }
        _styleRecyclerViewModels.value = vms
    }
}
