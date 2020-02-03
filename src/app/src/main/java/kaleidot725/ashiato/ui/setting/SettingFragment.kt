package kaleidot725.ashiato.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.SettingFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SettingFragment : Fragment() {
    private val viewModel: SettingViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<SettingFragmentBinding>(inflater, R.layout.setting_fragment, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.load()
    }

    companion object {
        fun newInstance() = SettingFragment()
    }
}
