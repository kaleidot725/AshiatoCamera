package kaleidot725.ashiato.ui.edit.confirm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.ConfirmFragmentBinding
import kaleidot725.ashiato.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ConfirmFragment : Fragment(), ConfirmNavigator {

    companion object {
        fun newInstance() = ConfirmFragment()
    }

    val viewModel: ConfirmViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.confirm_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DataBindingUtil.bind<ConfirmFragmentBinding>(view)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel
        viewModel.navigator = this
        viewModel.load()
    }

    override fun exit() {
        val intent = Intent(this.context, MainActivity::class.java)
        startActivity(intent)
    }
}
