package kaleidot725.daycamera.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kaleidot725.daycamera.R
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contact_button.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_contactFragment)
        }

        config_button.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_configFragment)
        }

        privacy_button.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_privacyFragment)
        }
    }
}
