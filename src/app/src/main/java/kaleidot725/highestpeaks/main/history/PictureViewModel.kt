package kaleidot725.highestpeaks.main.history

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kaleidot725.highestpeaks.model.repository.Picture
import kaleidot725.michetimer.model.repository.PictureRepository
import java.lang.Exception

class PictureViewModel(picture : Picture) : ViewModel() {
    private val picture : Picture = picture

    private val _name : MutableLiveData<String> = MutableLiveData()
    val name : LiveData<String> = _name

    private val _path : MutableLiveData<String> = MutableLiveData()
    val path : LiveData<String> get() = _path
}

@BindingAdapter("app:imageUrl")
fun loadImage(view : ImageView, imagePath : String?) {
//    try {
//        Picasso.get().load(imagePath).into(view)
//    }
//    catch ( e : Exception) {
//        print(e.toString())
//    }
}



