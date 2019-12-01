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

    companion object {
        fun newInstance() = SettingFragment()
    }

    val viewModel: SettingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.load()

        val binding = DataBindingUtil.bind<SettingFragmentBinding>(this.view as View)
        binding?.vm = viewModel
        binding?.lifecycleOwner = this
    }
}
