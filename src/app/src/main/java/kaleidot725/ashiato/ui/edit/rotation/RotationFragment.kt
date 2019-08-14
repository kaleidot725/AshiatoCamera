package kaleidot725.ashiato.ui.edit.rotation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kaleidot725.ashiato.R

class RotationFragment : Fragment() {

    companion object {
        fun newInstance() = RotationFragment()
    }

    private lateinit var viewModel: RotationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rotation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RotationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
