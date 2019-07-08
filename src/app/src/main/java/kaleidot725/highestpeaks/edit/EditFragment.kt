package kaleidot725.highestpeaks.edit

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
import java.io.File
import java.io.FileOutputStream
import android.media.MediaActionSound
import android.os.Environment
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.databinding.EditFragmentBinding
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named


class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }

    @Inject
    @field:Named("EditPicture")
    lateinit var editPicture : Holder<Picture>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val canvas = view.findViewById<EditCanvas>(R.id.edit_canvas)
        canvas.drawBitmap(editPicture.value)
    }
}
