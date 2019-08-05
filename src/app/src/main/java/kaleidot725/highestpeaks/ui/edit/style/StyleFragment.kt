package kaleidot725.highestpeaks.ui.edit.style

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kaleidot725.highestpeaks.R

class StyleFragment : Fragment() {

    companion object {
        fun newInstance() = StyleFragment()
    }

    private lateinit var viewModel: StyleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.style_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StyleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
