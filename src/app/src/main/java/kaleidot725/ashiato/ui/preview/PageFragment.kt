package kaleidot725.ashiato.ui.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.PreviewFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PageFragment : Fragment() {

    companion object {
        fun newInstance(position: Int) = PageFragment().also {
            val bundle = Bundle()
            bundle.putInt("position", position)
            it.arguments = bundle
        }
    }

    val viewModel: PageViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<PreviewFragmentBinding>(inflater, R.layout.preview_fragment, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt("position") ?: 0
        viewModel.load(position)
    }
}
