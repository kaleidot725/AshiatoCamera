package kaleidot725.ashiato.ui.edit.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.ConfirmFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ConfirmFragment : Fragment() {

    companion object {
        fun newInstance() = ConfirmFragment()
    }

    private val viewModel: ConfirmViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<ConfirmFragmentBinding>(
            inflater,
            R.layout.confirm_fragment,
            container,
            false
        ).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // FIXME EditActivityに埋め込む必要がある。
        viewModel.loadState()
        super.onViewCreated(view, savedInstanceState)
    }
}
