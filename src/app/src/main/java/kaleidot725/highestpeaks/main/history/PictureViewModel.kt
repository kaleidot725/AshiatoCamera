package kaleidot725.highestpeaks.main.history

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kaleidot725.highestpeaks.MyApplicationNavigator
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture

class PictureViewModel(private val navigation : MyApplicationNavigator,
                       private val picture : Picture,
                       private val preview : Holder<Picture>) : ViewModel() {

    private val _name : MutableLiveData<String> = MutableLiveData()
    val name : LiveData<String> = _name

    private val _path : MutableLiveData<String> = MutableLiveData()
    val path : LiveData<String> get() = _path

    init {
        _path.value = picture.path
        _name.value = picture.name
    }

    fun preview(view : View)  {
        preview.value = this.picture
        navigation.navigatePreview()
    }
}





