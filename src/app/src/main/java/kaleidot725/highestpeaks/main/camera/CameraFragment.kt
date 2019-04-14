package kaleidot725.highestpeaks.main.camera

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.camerakit.CameraKitView
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.CameraFragmentBinding
import java.io.File
import java.io.FileOutputStream
import android.media.MediaActionSound
import android.media.AudioManager
import android.content.Context
import android.os.Environment
import java.util.*


class CameraFragment : Fragment() {

    companion object {
        fun newInstance() = CameraFragment()
    }

    private lateinit var cameraKitView : CameraKitView
    private lateinit var viewModel: CameraViewModel
    private val imageCallback = object : CameraKitView.ImageCallback{
        override fun onImage(cameraKitView: CameraKitView?, capturedImage: ByteArray?) {
            try {
                val dcimPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                val dirPath = "${dcimPath}/Highest-Peak"
                val dirFile = File(dirPath)
                val b = dirFile.exists()
                if (!b) {
                    dirFile.mkdirs()
                }

                val photoPath = "${dirPath}/IMG_${Date()}.jpg"
                val outputStream = FileOutputStream(photoPath)
                outputStream.write(capturedImage)
                outputStream.close()

                val mSound = MediaActionSound()
                mSound.play(MediaActionSound.SHUTTER_CLICK)
            } catch (e: java.io.IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.camera_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CameraViewModel::class.java)
        val binding = DataBindingUtil.bind<CameraFragmentBinding>(view)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this

        cameraKitView = view.findViewById(R.id.camera_kit_view)
        viewModel.facing.observe(this, Observer { setFacing(it) })
        viewModel.flash.observe(this, Observer { setFlash(it)})
        viewModel.capture = { capture() }
    }

    override fun onStart() {
        super.onStart()
        cameraKitView.onStart()
    }

    override fun onResume() {
        super.onResume()
        cameraKitView.onResume()
    }

    override fun onPause() {
        cameraKitView.onPause()
        super.onPause()
    }

    override fun onStop() {
        cameraKitView.onStop()
        super.onStop()
    }

    fun setFacing(value : Int) {
        cameraKitView.facing = value
    }

    fun setFlash(value : Int) {
        cameraKitView.flash = value
    }

    fun capture() {
        cameraKitView.captureImage(imageCallback)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
