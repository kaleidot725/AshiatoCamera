package kaleidot725.highestpeaks.ui

import android.app.PendingIntent.getActivity
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import java.io.File

@BindingAdapter("app:imageUrl")
fun loadImage(view : ImageView, imagePath : String?) {
    val file = File(imagePath)
    Picasso.get().load(file).fit().centerInside().into(view)
}

@BindingAdapter("app:imageUrlNoCache")
fun loadImageNoCache(view : ImageView, imagePath : String?) {
    val file = File(imagePath)
    Picasso.get().load(file).memoryPolicy(MemoryPolicy.NO_CACHE).fit().centerInside().into(view)
}
