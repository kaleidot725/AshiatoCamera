package kaleidot725.ashiato.ui.edit.style

import android.widget.SeekBar
import androidx.lifecycle.ViewModel
import kaleidot725.ashiato.data.repository.StyleRepository
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.StyleEditor

class StyleViewModel(
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val styleRepository: StyleRepository
) : ViewModel() {

    val value: Int = (styleEditor.lastEnabled.dp / 2).toInt()
    val changedListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            val style =
                styleRepository.all().firstOrNull { s -> s.dp.toInt() == (seekBar.progress * 2) }
            style?.let {
                styleEditor.enable(it)
                pictureEditor.modifyTextSize(it.dp)
                pictureEditor.commit()
            }
        }
    }

    fun load() {
    }
}
