package kaleidot725.daycamera.ui.preview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kaleidot725.daycamera.R
import kaleidot725.daycamera.databinding.FragmentPreviewBinding

class PreviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentPreviewBinding>(inflater, LAYOUT_ID, container, false).apply {
            viewModel = PreviewViewModel()
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    companion object {
        private const val LAYOUT_ID = R.layout.fragment_preview
    }
}
