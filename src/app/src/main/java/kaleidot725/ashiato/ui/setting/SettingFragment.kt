package kaleidot725.ashiato.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.SettingFragmentBinding
import kaleidot725.ashiato.di.service.location.PermanentLocationSetting
import javax.inject.Inject

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private lateinit var viewModel: SettingViewModel

    @Inject
    lateinit var persistenceSetting: PermanentLocationSetting

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, SettingViewModelFactory(persistenceSetting)).get(SettingViewModel::class.java)
        val binding = DataBindingUtil.bind<SettingFragmentBinding>(this.view as View)
        binding?.vm = viewModel
        binding?.lifecycleOwner = this
    }
}
