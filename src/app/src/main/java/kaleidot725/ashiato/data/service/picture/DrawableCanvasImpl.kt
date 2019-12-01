package kaleidot725.ashiato.data.service.picture

import android.graphics.*
import java.io.File
import java.io.FileOutputStream


class DrawableCanvasImpl() : DrawableCanvas {
    private var currentPath: String = ""
    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas

    override fun load(path: String) {
        val options = BitmapFactory.Options().also {
            it.inMutable = true
        }

        bitmap = BitmapFactory.decodeFile(path, options)
        canvas = Canvas(bitmap).also { it.drawBitmap(bitmap, 0f, 0f, Paint()) }
        currentPath = path
    }

    override fun draw(x: Float, y: Float, text: String, color: Int, size: Float) {
        val strokePaint = createStrokePaint(size)
        canvas.drawText(text, x, y, strokePaint)

        val textPaint = Paint().also {
            it.color = color
            it.isAntiAlias = true
            it.textSize = size
        }

        canvas.drawText(text, x, y, textPaint)
    }

    override fun draw(pos: PositionType, text: String, color: Int, size: Float) {
        val strokePaint = createStrokePaint(size)
        canvas.drawText(
            text,
            calcX(pos, text, strokePaint),
            calcY(pos, text, strokePaint),
            strokePaint
        )

        val textPaint = Paint().also {
            it.color = color
            it.isAntiAlias = true
            it.textSize = size
        }

        canvas.drawText(text, calcX(pos, text, textPaint), calcY(pos, text, textPaint), textPaint)
    }

    override fun rotation(degree: Float) {
        val matrix = Matrix().also { it.postRotate(degree) }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        canvas = Canvas(bitmap).also { it.drawBitmap(bitmap, 0f, 0f, Paint()) }
    }

    override fun write(path: String) {
        val out = FileOutputStream(File(path))
        this.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        out.flush()
        out.close()
    }

    override fun delete(path: String) {
        File(path).delete()
    }

    private fun createStrokePaint(fontSize: Float): Paint = Paint().also {
        it.color = android.graphics.Color.WHITE
        it.textSize = fontSize
        it.isAntiAlias = true
        it.strokeWidth = calcStrokeWidth(fontSize)
        it.strokeJoin = Paint.Join.ROUND
        it.strokeCap = Paint.Cap.ROUND
        it.style = Paint.Style.STROKE
    }

    private fun calcStrokeWidth(fontSize: Float): Float {
        return fontSize / 100f
    }

    private fun calcX(position: PositionType, text: String, paint: Paint): Float {
        val bounds = Rect().also {
            paint.getTextBounds(text, 0, text.length, it)
        }

        var margin = if (text.isEmpty()) {
            0f
        } else {
            bounds.width().toFloat() / text.length.toFloat() / 2f
        }

        when (position) {
            PositionType.TopLeft -> return 0f
            PositionType.TopCenter -> return (canvas.width / 2 - bounds.width() / 2).toFloat()
            PositionType.TopRight -> return (canvas.width - bounds.width()).toFloat() - margin
            PositionType.CenterLeft -> return 0f
            PositionType.Center -> return (canvas.width / 2 - bounds.width() / 2).toFloat()
            PositionType.CenterRight -> return (canvas.width - bounds.width()).toFloat() - margin
            PositionType.BottomLeft -> return 0f
            PositionType.BottomCenter -> return (canvas.width / 2 - bounds.width() / 2).toFloat()
            PositionType.BottomRight -> return (canvas.width - bounds.width()).toFloat() - margin
        }
    }

    private fun calcY(position: PositionType, text: String, paint: Paint): Float {
        val bounds = Rect().also {
            paint.getTextBounds(text, 0, text.length, it)
        }

        var margin = if (text.isEmpty()) {
            0f
        } else {
            bounds.height().toFloat() / 8f
        }

        when (position) {
            PositionType.TopLeft -> return bounds.height().toFloat()
            PositionType.TopCenter -> return bounds.height().toFloat()
            PositionType.TopRight -> return bounds.height().toFloat()
            PositionType.CenterLeft -> return (canvas.height / 2 - bounds.height() / 2).toFloat()
            PositionType.Center -> return (canvas.height / 2 - bounds.height() / 2).toFloat()
            PositionType.CenterRight -> return (canvas.height / 2 - bounds.height() / 2).toFloat()
            PositionType.BottomLeft -> return canvas.height.toFloat() - margin
            PositionType.BottomCenter -> return canvas.height.toFloat() - margin
            PositionType.BottomRight -> return canvas.height.toFloat() - margin
        }
    }
}