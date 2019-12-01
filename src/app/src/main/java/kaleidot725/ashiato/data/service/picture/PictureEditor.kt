package kaleidot725.ashiato.data.service.picture

import io.reactivex.subjects.Subject

interface PictureEditor {
    var preview: Picture?
    var target: Picture?
    val state: Subject<PictureEditorState>

    fun start(target: Picture, preview: Picture)
    fun modifyText(text: String)
    fun modifyTextSize(size: Float)
    fun modifyColor(color: Int)
    fun modifyPosition(position: PositionType)
    fun modifyRotation(angle: Float)
    fun end()
    fun cancel()
}