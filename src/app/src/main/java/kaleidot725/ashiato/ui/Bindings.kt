package kaleidot725.ashiato.ui

import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.request.CachePolicy
import java.io.File

@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, imagePath: String?) {
    if (imagePath == null) {
        return
    }

    val file = File(imagePath)
    view.load(file) {
        crossfade(true)
    }
}

@BindingAdapter("app:imageUrlNoCache")
fun loadImageNoCache(view: ImageView, imagePath: String?) {
    if (imagePath == null) {
        return
    }
    
    val file = File(imagePath)
    view.load(file) {
        crossfade(true)
        diskCachePolicy(CachePolicy.DISABLED)
        memoryCachePolicy(CachePolicy.DISABLED)
        networkCachePolicy(CachePolicy.DISABLED)
    }
}

@BindingAdapter("app:onSafeClick")
fun onSafeClick(view: View, listener: View.OnClickListener) {

    class SafeClickListener(
        private var defaultInterval: Int = 1000,
        private val listener: View.OnClickListener
    ) : View.OnClickListener {
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