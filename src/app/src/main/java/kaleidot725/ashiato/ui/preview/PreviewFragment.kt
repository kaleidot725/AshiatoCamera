package kaleidot725.ashiato.ui.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.PreviewFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PreviewFragment : Fragment() {
    private val viewModel: PreviewViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<PreviewFragmentBinding>(inflater, R.layout.preview_fragment, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager)
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return viewModel.pageCount.value ?: 0
            }

            override fun createFragment(position: Int): Fragment {
                return PageFragment.newInstance(position)
            }
        }
        viewPager.currentItem = viewModel.currentPage.value ?: 0
    }
}