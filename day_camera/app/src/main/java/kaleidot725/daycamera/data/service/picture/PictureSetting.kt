package kaleidot725.daycamera.data.service.picture

data class PictureSetting(
    val color: Color,
    val style: Style,
    val formats: List<Format>,
    val position: Position,
    val angle: Angle
)