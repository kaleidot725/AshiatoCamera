package kaleidot725.daycamera.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kaleidot725.daycamera.R
import kaleidot725.daycamera.databinding.FragmentHistoryBinding
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentHistoryBinding>(inflater, LAYOUT_ID, container, false).apply {
            viewModel = HistoryViewModel()
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        got_it_button.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_previewFragment)
        }
    }

    companion object {
        private const val LAYOUT_ID = R.layout.fragment_history
    }
}
