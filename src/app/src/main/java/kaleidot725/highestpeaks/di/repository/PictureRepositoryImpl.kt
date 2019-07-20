package kaleidot725.michetimer.model.repository

import android.os.Environment
import androidx.databinding.ObservableArrayList
import kaleidot725.highestpeaks.di.data.Picture
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PictureRepositoryImpl(path : String) : PictureRepository {
    private val path  = path

    override fun all() : List<Picture> {
        return update().reversed()
    }

    override fun count() : Int {
        val list = update()
        return list.count()
    }

    override fun newPicture(): Picture {
        val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val dirPath = "${dcim}/Highest-Peak"
        val df = SimpleDateFormat("yyyyMMdd HH:mm:ss")
        val name = "IMG_${df.format(Date())}.jpg"
        val path = "${dirPath}/${name}"
        return Picture(path, name)
    }

    private fun update() : ObservableArrayList<Picture>{
        val list = ObservableArrayList<Picture>()
        File(path).walkTopDown().forEach {
            if (it.path != path) {
                list.add(Picture(it.path, it.name))
            }
        }

        return list
    }
}