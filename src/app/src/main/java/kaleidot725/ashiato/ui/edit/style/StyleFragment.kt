package kaleidot725.ashiato.ui.edit.style

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.StyleFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class StyleFragment : Fragment() {

    companion object {
        fun newInstance() = StyleFragment()
    }

    private val styleViewModel: StyleViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<StyleFragmentBinding>(inflater, R.layout.style_fragment, container, false)
            .apply {
                vm = styleViewModel
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
