package kaleidot725.highestpeaks.ui.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kaleidot725.highestpeaks.R
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.databinding.EditFragmentBinding
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.ui.main.home.HomeViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.*
import java.util.*


class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }

    @Inject
    @field:Named("EditPicture")
    lateinit var editPicture : Holder<Picture>

    @Inject
    lateinit var locationRepository: LocationRepository

    private lateinit var viewModel : EditViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    lateinit var createdView : View
    lateinit var handler : Handler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, EditViewModelFactory()).get(EditViewModel::class.java)

        val binding = DataBindingUtil.bind<EditFragmentBinding>(view)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel

        createdView = view
        handler = Handler(Looper.getMainLooper())
        view.viewTreeObserver.addOnGlobalLayoutListener {
            handler.post(object : Runnable {
                override fun run() {
                    val text = "${Date().toString()}    ${locationRepository.lastAltitude?.toInt()}m"
                    val canvas = view.findViewById<EditCanvas>(R.id.edit_canvas)
                    canvas.drawPicture(editPicture.value, text, createdView.width, createdView.height )
                }
            })
        }
    }
}
