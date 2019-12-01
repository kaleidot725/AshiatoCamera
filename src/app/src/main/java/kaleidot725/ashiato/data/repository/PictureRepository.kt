package kaleidot725.ashiato.data.repository

import kaleidot725.ashiato.data.service.picture.Picture

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

    fun action(picture: Picture)
    val actioned: Picture?

    fun preview(picture: Picture)
    val previewed: Picture?
}