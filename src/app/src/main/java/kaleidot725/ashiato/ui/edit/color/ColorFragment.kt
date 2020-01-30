package kaleidot725.ashiato.ui.edit.color

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
import kaleidot725.ashiato.databinding.ColorFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ColorFragment : Fragment() {

    companion object {
        fun newInstance() = ColorFragment()
    }

    private val colorViewModel: ColorViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<ColorFragmentBinding>(
            inflater,
            R.layout.color_fragment,
            container,
            false
        ).apply {
            vm = colorViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = this.view?.findViewById<RecyclerView>(R.id.color_recycler_View)
        colorViewModel.colorRecyclerViewModels.observe(this, Observer {
            recyclerView?.adapter = ColorRecyclerAdapter(this, colorViewModel.colorRecyclerViewModels.value ?: listOf())
            recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView?.setHasFixedSize(true)
        })
        colorViewModel.load()
    }
}
