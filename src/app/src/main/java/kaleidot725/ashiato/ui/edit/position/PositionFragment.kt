package kaleidot725.ashiato.ui.edit.position

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
import kaleidot725.ashiato.databinding.PositionFragmentBindingImpl
import org.koin.android.viewmodel.ext.android.viewModel

class PositionFragment : Fragment() {

    companion object {
        fun newInstance() = PositionFragment()
    }

    val positionViewModel: PositionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.position_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val binding = DataBindingUtil.bind<PositionFragmentBindingImpl>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = positionViewModel

        val recyclerView = this.view?.findViewById<(RecyclerView)>(R.id.position_recycler_view)
        positionViewModel.positionRecyclerViewModels.observe(this, Observer {
            recyclerView?.adapter = PositionRecyclerAdapter(
                this,
                positionViewModel.positionRecyclerViewModels.value ?: listOf()
            )
            recyclerView?.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView?.setHasFixedSize(true)
        })

        positionViewModel.load()
    }
}
