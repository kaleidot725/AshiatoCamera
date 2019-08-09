package kaleidot725.highestpeaks.di.service

import android.graphics.*
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kaleidot725.highestpeaks.di.data.Picture
import java.lang.Exception

class PictureEditorImpl(drwableCnavas : DrawableCanvas) : PictureEditor {

    override var target: Picture? = null
    override var preview: Picture? = null

    private var _state : PublishSubject<PictureEditorState> = PublishSubject.create()
    override val state: Subject<PictureEditorState> get() = _state
    private var lastState : PictureEditorState = PictureEditorState.Init

    private var canvas : DrawableCanvas = drwableCnavas
    private var text : String = ""
    private var color : Int = Color.WHITE
    private var textSize : Float = 64f
    private var postion : Int = 0
    private var degree : Int = 0

    override fun start(target : Picture, preview : Picture) {
        if (lastState != PictureEditorState.Init) {
            throw Exception("invalid operation")
        }

        this.target = target
        this.preview = preview

        canvas.load(target.path)
        canvas.write(preview.path)

        lastState = PictureEditorState.Start
        _state.onNext(PictureEditorState.Start)
    }

    override fun modifyText(text: String) {
        if (lastState == PictureEditorState.Init) {
            throw Exception("invalid operation")
        }

        this.text = text
        canvas.load(target!!.path)
        canvas.draw(0f, this.textSize, this.text, this.color, this.textSize)
        canvas.write(preview!!.path)

        lastState = PictureEditorState.Update
        _state.onNext(PictureEditorState.Update)
    }

    override fun modifyTextSize(size: Float) {
        if (lastState == PictureEditorState.Init) {
            throw Exception("invalid operation")
        }

        this.textSize = size
        canvas.load(target!!.path)
        canvas.draw(0f, this.textSize, this.text, this.color, this.textSize)
        canvas.write(preview!!.path)

        lastState = PictureEditorState.Update
        _state.onNext(PictureEditorState.Update)
    }

    override fun modifyColor(color: Int) {
        if (lastState == PictureEditorState.Init) {
            throw Exception("invalid operation")
        }

        this.color = color
        canvas.load(target!!.path)
        canvas.draw(0f, this.textSize, this.text, this.color, this.textSize)
        canvas.write(preview!!.path)

        lastState = PictureEditorState.Update
        _state.onNext(PictureEditorState.Update)
    }

    override fun modifyPosition(position: Int) {
        if (lastState == PictureEditorState.Init) {
            throw Exception("invalid operation")
        }

        this.postion = postion
        canvas.load(target!!.path)
        canvas.draw(0f, this.textSize, this.text, this.color, this.textSize)
        canvas.write(preview!!.path)

        lastState = PictureEditorState.Update
        _state.onNext(PictureEditorState.Update)
    }

    override fun modifyRotation(degree: Int) {
        if (lastState == PictureEditorState.Init) {
            throw Exception("invalid operation")
        }

        this.degree = degree
        canvas.load(target!!.path)
        canvas.draw(0f, this.textSize, this.text, this.color, this.textSize)
        canvas.write(preview!!.path)

        lastState = PictureEditorState.Update
        _state.onNext(PictureEditorState.Update)
    }

    override fun end() {
        if (lastState == PictureEditorState.Init) {
            throw Exception("invalid operation")
        }

        canvas.delete(preview!!.path)
        canvas.write(target!!.path)
        lastState = PictureEditorState.Init
        _state.onComplete()
        _state = PublishSubject.create()
    }

    override fun cancel() {
        if (lastState != PictureEditorState.Init &&
            lastState != PictureEditorState.Update) {
            throw Exception("invalid operation")
        }

        canvas.delete(preview!!.path)
        lastState = PictureEditorState.Init
        _state.onComplete()
        _state = PublishSubject.create()
    }
}
