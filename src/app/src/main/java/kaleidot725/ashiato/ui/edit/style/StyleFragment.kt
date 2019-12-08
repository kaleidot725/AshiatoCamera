package kaleidot725.ashiato.ui.edit.style

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.StyleFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class StyleFragment : Fragment() {

    companion object {
        fun newInstance() = StyleFragment()
    }

    val styleViewModel: StyleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.style_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DataBindingUtil.bind<StyleFragmentBinding>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = styleViewModel

//        val recyclerView = this.view?.findViewById<RecyclerView>(R.id.style_recycler_view)
//        styleViewModel.styleRecyclerViewModel.observe(this, Observer {
//            recyclerView?.adapter =
//                StyleRecyclerAdapter(this, styleViewModel.styleRecyclerViewModel.value ?: listOf())
//            recyclerView?.layoutManager =
//                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//            recyclerView?.setHasFixedSize(true)
//        })

        
        styleViewModel.load()
    }
}
