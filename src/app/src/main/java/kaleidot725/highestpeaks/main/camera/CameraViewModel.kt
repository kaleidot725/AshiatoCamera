package kaleidot725.highestpeaks.main.camera

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camerakit.CameraKit
import kaleidot725.highestpeaks.R

class CameraViewModel : ViewModel() {

    val facing : MutableLiveData<Int> = MutableLiveData()
    val flash : MutableLiveData<Int> = MutableLiveData()
    var flashIcon : MutableLiveData<Int> = MutableLiveData()
    var capture : (()-> Unit)? = null

    init {
        facing.postValue(CameraKit.FACING_BACK)
        flash.postValue(CameraKit.FLASH_OFF)
        flashIcon.postValue(R.drawable.ic_flash_off)
    }

    fun clickCapture(view : View) {
        capture?.invoke()
    }

    fun toggleFacing(view : View) {
        when(facing.value) {
            CameraKit.FACING_BACK -> {
                facing.postValue(CameraKit.FACING_FRONT)
            }
            CameraKit.FACING_FRONT -> {
                facing.postValue(CameraKit.FACING_BACK)
            }
            else -> {
                facing.postValue(CameraKit.FACING_BACK)
            }
        }
    }

    fun toggleFlash(view : View) {
        when (flash.value) {
            CameraKit.FLASH_OFF -> {
                flash.postValue(CameraKit.FLASH_ON)
                flashIcon.postValue( R.drawable.ic_flash_on)
            }
            CameraKit.FLASH_ON -> {
                flash.postValue(CameraKit.FLASH_AUTO)
                flashIcon.postValue(R.drawable.ic_flash_auto)
            }
            CameraKit.FLASH_AUTO -> {
                flash.postValue(CameraKit.FLASH_OFF)
                flashIcon.postValue(R.drawable.ic_flash_off)
            }
            else -> {
                flash.postValue(CameraKit.FLASH_OFF)
                flashIcon.postValue(R.drawable.ic_flash_off)
            }
        }
    }
}
