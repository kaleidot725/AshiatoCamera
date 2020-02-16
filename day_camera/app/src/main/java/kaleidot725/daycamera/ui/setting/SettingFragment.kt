package kaleidot725.daycamera.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import kaleidot725.daycamera.R
import kaleidot725.daycamera.data.service.contact.MenuType
import kaleidot725.daycamera.databinding.FragmentSettingBinding
import kotlinx.android.synthetic.main.fragment_setting.*
import org.koin.android.viewmodel.ext.android.viewModel

class SettingFragment : Fragment() {
    private val viewModel: SettingViewModel by viewModel()
    private val adapter: SettingAdapter = SettingAdapter().apply { onClick = { navigateSetting(it) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentSettingBinding>(inflater, LAYOUT_ID, container, false).apply {
            viewModel = this.viewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)

        viewModel.menuList.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
        })

        viewModel.fetchMenu()
    }

    private fun navigateSetting(type: MenuType) {
        when (type) {
            MenuType.CONFIG -> {
                findNavController().navigate(R.id.action_settingFragment_to_configFragment)
            }
            MenuType.LICENSE -> {
                LibsBuilder()
                    .withActivityTitle("License")
                    .withShowLoadingProgress(false)
                    .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR).start(context)
            }
            MenuType.CONTACT -> {
                findNavController().navigate(R.id.action_settingFragment_to_contactFragment)
            }
            MenuType.PRIVACY_POLICY -> {
                findNavController().navigate(R.id.action_settingFragment_to_privacyFragment)
            }
        }
    }

    companion object {
        private const val LAYOUT_ID = R.layout.fragment_setting
    }
}
