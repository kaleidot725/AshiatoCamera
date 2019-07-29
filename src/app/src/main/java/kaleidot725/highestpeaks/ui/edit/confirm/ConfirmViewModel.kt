package kaleidot725.highestpeaks.ui.edit.confirm

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kaleidot725.highestpeaks.di.service.FormatEditor
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.ui.edit.EditNavigator
import kaleidot725.michetimer.model.repository.PictureRepository

class ConfirmViewModel(
    val navigator: EditNavigator,
    val pictureRepository: PictureRepository,
    val formatEditor : FormatEditor,
    val pictureEditor : PictureEditor
) : ViewModel() {

    private val _editPath : MutableLiveData<String> = MutableLiveData()
    val editPath : LiveData<String> = _editPath

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    init {
        _editPath.value = pictureRepository.took!!.path
        val test= pictureEditor.start(pictureRepository.took!!.path)
        val disposable = test.subscribe { _editPath.postValue(it) }
        compositeDisposable.add(disposable)
        pictureEditor.modifyText(formatEditor.create())
    }

    fun save(view : View) {
        pictureEditor.end(pictureRepository.took!!.path)
        navigator.exit()
    }

    fun cancel(view : View) {
        pictureEditor.cancel()
        navigator.exit()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}