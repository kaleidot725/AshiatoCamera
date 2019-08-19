package kaleidot725.ashiato.di.service

import kaleidot725.ashiato.di.data.PositionType

interface DrawableCanvas {
    fun load(path: String)
    fun draw(x: Float, y: Float, text: String, color: Int, size: Float)
    fun draw(pos: PositionType, text: String, color: Int, size: Float)
    fun rotation(degree: Float)
    fun write(path: String)
    fun delete(path: String)
}