package kaleidot725.highestpeaks.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kaleidot725.highestpeaks.R
import android.os.Handler
import android.os.Looper
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture
import kaleidot725.highestpeaks.model.repository.LocationRepository
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

    lateinit var createdView : View
    lateinit var handler : Handler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
