package kaleidot725.ashiato.ui

import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import java.io.File

@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, imagePath: String?) {
    val file = File(imagePath)
    Picasso.get().load(file).fit().centerInside().into(view)
}

@BindingAdapter("app:imageUrlNoCache")
fun loadImageNoCache(view: ImageView, imagePath: String?) {
    val file = File(imagePath)
    Picasso.get().load(file).memoryPolicy(MemoryPolicy.NO_CACHE).fit().centerInside().into(view)
}

@BindingAdapter("app:onSafeClick")
fun onSafeClick(view: View,  listener : View.OnClickListener) {

    class SafeClickListener(
        private var defaultInterval: Int = 1000,
        private val listener : View.OnClickListener) : View.OnClickListener
    {
        private var lastTimeClicked: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
                return
            }
            lastTimeClicked = SystemClock.elapsedRealtime()
            listener.onClick(v)
        }
    }

    view.setOnClickListener(SafeClickListener(1000, listener))
}