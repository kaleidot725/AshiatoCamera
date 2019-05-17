package kaleidot725.michetimer.model.repository

import kaleidot725.highestpeaks.model.data.Picture

interface PictureRepository {
    fun all() : List<Picture>
    fun count() : Int
}