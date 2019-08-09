package kaleidot725.michetimer.model.repository

import android.os.Environment
import kaleidot725.highestpeaks.di.data.Picture
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PictureRepositoryImpl(path : String) : PictureRepository {
    private val path  = path

    override var took: Picture? = null
        private set

    override var actioned: Picture? = null
        private set

    override var previewed: Picture? = null
        private set

    override fun all() : List<Picture> {
        return update().reversed()
    }

    override fun count() : Int {
        val list = update()
        return list.count()
    }

    override fun tmpPicture(): Picture {
        val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val dirPath = "${dcim}/Highest-Peak"
        val name = "tmp.jpg"
        val path = "${dirPath}/${name}"
        return Picture(path, name)
    }

    override fun newPicture(): Picture {
        val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val dirPath = "${dcim}/Highest-Peak"
        val df = SimpleDateFormat("yyyyMMdd HH:mm:ss")
        val name = "IMG_${df.format(Date())}.jpg"
        val path = "${dirPath}/${name}"
        return Picture(path, name)
    }

    override fun action(picture: Picture) {
        actioned = picture
    }

    override fun take(picture: Picture) {
        took = picture
    }

    override fun preview(picture: Picture) {
        previewed = picture
    }

    private fun update() : List<Picture>{
        val list = ArrayList<Picture>()
        File(path).walkTopDown().forEach {
            if (it.path != path) {
                list.add(Picture(it.path, it.name))
            }
        }

        return list.sortedWith(compareBy { it.name })
    }
}