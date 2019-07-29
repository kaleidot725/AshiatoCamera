package kaleidot725.highestpeaks.di.service

import android.graphics.*
import android.media.ExifInterface
import java.io.File
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.io.FileOutputStream
import java.lang.Exception

class PictureEditorImpl : PictureEditor {
    enum class PictureEditorState {
        Init,
        Edit,
    }

    private var state : PictureEditorState = PictureEditorState.Init
    private lateinit var bitmap : Bitmap
    private lateinit var canvas : Canvas
    private lateinit var subject: PublishSubject<String>

    private var text : String = ""
    private var color : Int = Color.WHITE
    private var textSize : Float = 64f
    private var postion : Int = 0
    private var degree : Int = 0
    private var preview: String = ""
    private var path : String = ""
    override fun start(path : String) : Subject<String> {
        if (state != PictureEditorState.Init) {
            throw Exception("invalid operation")
        }

        this.path = path
        preview = path + ".temp"
        init()
        state = PictureEditorState.Edit

        this.subject = PublishSubject.create()
        return this.subject
    }

    override fun modifyText(text: String) {
        if (state != PictureEditorState.Edit) {
            throw Exception("invalid operation")
        }

        this.text = text
        init()
        draw()
        save(preview)
        this.subject.onNext(preview)
    }

    override fun modifyColor(color: Int) {
        if (state != PictureEditorState.Edit) {
            throw Exception("invalid operation")
        }

        this.color = color
        init()
        draw()
        save(preview)
        this.subject.onNext(preview)
    }

    override fun modifyPosition(position: Int) {
        if (state != PictureEditorState.Edit) {
            throw Exception("invalid operation")
        }

        this.postion = postion
        init()
        draw()
        save(preview)
        this.subject.onNext(preview)
    }

    override fun modifyRotation(degree: Int) {
        if (state != PictureEditorState.Edit) {
            throw Exception("invalid operation")
        }

        this.degree = degree
        init()
        draw()
        save(preview)
        this.subject.onNext(preview)
    }

    override fun end(path: String) {
        if (state != PictureEditorState.Edit) {
            throw Exception("invalid operation")
        }

        save(path)
        File(preview).delete()
        state = PictureEditorState.Init
        this.subject.onComplete()
    }

    override fun cancel() {
        if (state != PictureEditorState.Edit) {
            throw Exception("invalid operation")
        }

        File(preview).delete()
        state = PictureEditorState.Init
        this.subject.onComplete()
    }

    private fun init() {
        val rotationAngle = getRotationAngle(path)
        val matrix = Matrix().also { it.postRotate(rotationAngle.toFloat()) }
        val options = BitmapFactory.Options().also { it.inMutable = true }
        val original = BitmapFactory.decodeFile(path, options)
        bitmap = Bitmap.createBitmap(original, 0, 0, original.width, original.height, matrix, true)
        canvas = Canvas(bitmap).also { it.drawBitmap(bitmap, 0f, 0f, Paint()) }
        canvas.save()

    }

    private fun draw() {
        val paint = Paint()
        paint.color = color
        paint.textSize = textSize
        canvas.drawText(this.text, 0f, textSize, paint)
    }

    private fun save(path : String) {
        val out = FileOutputStream(File(path))
        this.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        out.flush()
        out.close()
    }

    private fun getRotationAngle(path : String) : Int {
        val exif = ExifInterface(path)
        val orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION)
        val orientation = if (orientString != null) Integer.parseInt(orientString!!) else ExifInterface.ORIENTATION_NORMAL

        if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
            return 90

        if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
            return 180

        if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
            return 270

        return 0
    }
}
