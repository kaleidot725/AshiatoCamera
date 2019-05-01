package kaleidot725.highestpeaks.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.io.File

@BindingAdapter("app:imageUrl")
fun loadImage(view : ImageView, imagePath : String?) {
    val file = File(imagePath)
    Picasso.get().load(file).into(view)
}