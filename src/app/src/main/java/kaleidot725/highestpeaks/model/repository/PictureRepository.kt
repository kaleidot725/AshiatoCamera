package kaleidot725.michetimer.model.repository

import kaleidot725.highestpeaks.model.data.Picture
import java.io.File

interface PictureRepository {
    fun all() : List<Picture>
    fun count() : Int
    fun newPicture() : Picture
}