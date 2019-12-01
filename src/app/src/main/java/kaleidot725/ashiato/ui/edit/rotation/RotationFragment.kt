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
import kaleidot725.ashiato.databinding.RotationFragmentBindingImpl
import org.koin.android.viewmodel.ext.android.viewModel

class RotationFragment : Fragment() {

    companion object {
        fun newInstance() = RotationFragment()
    }

    val rotationViewModel: RotationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rotation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DataBindingUtil.bind<RotationFragmentBindingImpl>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = rotationViewModel

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
