package kaleidot725.ashiato.ui.edit.style

import android.widget.SeekBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kaleidot725.ashiato.data.repository.StyleRepository
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.StyleEditor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StyleViewModel(
    private val pictureEditor: PictureEditor,
    private val styleEditor: StyleEditor,
    private val styleRepository: StyleRepository
) : ViewModel() {
    val min: Int = 1
    val max: Int = 100
    val ratio: Float = (styleEditor.max - styleEditor.min) / (max - min).toFloat()
    val value: Int = (styleEditor.lastEnabled.dp / ratio).toInt()

    val changedListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            viewModelScope.launch(Dispatchers.Default) {
                val style = styleRepository.all().firstOrNull { s ->
                    s.dp.toInt() == (seekBar.progress * ratio).toInt()
                }
                style?.let {
                    styleEditor.enable(it)
                    pictureEditor.modifyTextSize(it.dp)
                    pictureEditor.commit()
                }
            }
        }
    }
}
