package kaleidot725.daycamera.data.repository

import kaleidot725.daycamera.data.service.picture.Picture

enum class EditType {
    FOLDER,
    TOOK,
}

interface PictureRepository {
    fun all(): List<Picture>
    fun count(): Int

    fun tmpPicture(): Picture
    fun newPicture(): Picture

    fun edit(type: EditType, picture: Picture)
    val editPicture: Picture?
    val editType: EditType?

    fun preview(picture: Picture)
    val previewed: Picture?
}