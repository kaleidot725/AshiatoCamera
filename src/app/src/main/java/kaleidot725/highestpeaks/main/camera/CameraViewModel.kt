package kaleidot725.highestpeaks.main.camera

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camerakit.CameraKit

class CameraViewModel : ViewModel() {

    val facing : MutableLiveData<Int> = MutableLiveData()
    val flash : MutableLiveData<Int> = MutableLiveData()
    var capture : (()-> Unit)? = null

    init {
        facing.postValue(CameraKit.FACING_BACK)
        flash.postValue(CameraKit.FLASH_OFF)
    }

    fun toggleFacing(view : View) {
        val value  = when(facing.value) {
            CameraKit.FACING_BACK -> CameraKit.FACING_FRONT
            else -> CameraKit.FACING_BACK
        }

        facing.postValue(value)
    }

    fun clickCapture(view : View) {
        capture?.invoke()
    }

    fun toggleFlash(view : View) {
        val value = when (flash.value) {
            CameraKit.FLASH_OFF -> CameraKit.FLASH_ON
            CameraKit.FLASH_ON -> CameraKit.FLASH_AUTO
            else -> CameraKit.FLASH_OFF
        }

        flash.postValue(value)
    }

}
