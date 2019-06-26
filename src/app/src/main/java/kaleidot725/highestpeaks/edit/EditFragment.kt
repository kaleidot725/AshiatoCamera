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
import java.text.SimpleDateFormat
import java.util.*


class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }

    private lateinit var cameraKitView : CameraKitView
    private lateinit var viewModel: EditViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)
        val binding = DataBindingUtil.bind<EditFragmentBinding>(view)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
    }
}
