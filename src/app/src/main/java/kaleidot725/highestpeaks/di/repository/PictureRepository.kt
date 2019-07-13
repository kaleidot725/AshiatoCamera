package kaleidot725.michetimer.model.repository

import kaleidot725.highestpeaks.di.data.Picture

interface PictureRepository {
    fun all() : List<Picture>
    fun count() : Int
    fun newPicture() : Picture
}