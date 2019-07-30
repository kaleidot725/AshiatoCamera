package kaleidot725.highestpeaks.di.service

interface DrawableCanvas {
    fun load(path : String)
    fun draw(x : Float, y : Float, text: String, color : Int, size : Float)
    fun rotation(degree : Float)

    fun write(path : String)
    fun delete(path : String)
}