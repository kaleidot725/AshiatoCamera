package kaleidot725.highestpeaks.ui.edit.format

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kaleidot725.highestpeaks.R

class FormatFragment : Fragment() {

    companion object {
        fun newInstance() = FormatFragment()
    }

    private lateinit var viewModel: FormatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.format_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FormatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
