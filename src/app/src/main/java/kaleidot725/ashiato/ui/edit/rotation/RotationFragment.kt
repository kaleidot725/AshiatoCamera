package kaleidot725.ashiato.ui.edit.rotation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.RotationFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RotationFragment : Fragment() {

    companion object {
        fun newInstance() = RotationFragment()
    }

    private val rotationViewModel: RotationViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<RotationFragmentBinding>(inflater, R.layout.rotation_fragment, container, false)
            .apply {
                vm = rotationViewModel
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = this.view?.findViewById<RecyclerView>(R.id.rotation_recycler_view)
        rotationViewModel.rotaionRecyclerViewModels.observe(this, Observer {
            recyclerView?.adapter =
                RotationRecyclerAdapter(
                    this,
                    rotationViewModel.rotaionRecyclerViewModels.value ?: listOf()
                )
            recyclerView?.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView?.setHasFixedSize(true)
        })

        rotationViewModel.load()
    }
}
