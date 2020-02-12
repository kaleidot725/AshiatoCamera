package kaleidot725.daycamera.ui.permission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kaleidot725.daycamera.R
import kaleidot725.daycamera.databinding.FragmentPermissionBinding
import kotlinx.android.synthetic.main.fragment_permission.*

class PermissionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentPermissionBinding>(inflater, LAYOUT_ID, container, false).apply {
            viewModel = PermissionViewModel()
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        got_it_button.setOnClickListener {
            findNavController().navigate(R.id.action_permissionFragment_to_homeFragment)
        }
    }

    companion object {
        private const val LAYOUT_ID = R.layout.fragment_permission
    }
}
