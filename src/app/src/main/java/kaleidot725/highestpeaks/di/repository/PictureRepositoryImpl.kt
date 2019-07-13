package kaleidot725.michetimer.model.repository

import android.os.Environment
import androidx.databinding.ObservableArrayList
import kaleidot725.highestpeaks.di.data.Picture
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PictureRepositoryImpl(path : String) : PictureRepository {
    private val path  = path
    private var list = ObservableArrayList<Picture>()

    override fun all() : List<Picture> {
        update()
        return this.list.toList()
    }

    override fun count() : Int {
        update()
        return this.list.count()
    }

    override fun newPicture(): Picture {
        val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val dirPath = "${dcim}/Highest-Peak"
        val df = SimpleDateFormat("yyyyMMdd HH:mm:ss")
        val name = "IMG_${df.format(Date())}.jpg"
        val path = "${dirPath}/${name}"
        return Picture(Picture.createID(), path, name)
    }

    private fun update() {
        list.clear()
        File(path).walkTopDown().forEach {
            if (it.path != path) {
                list.add(Picture(Picture.createID(), it.path, it.name))
            }
        }
    }
}