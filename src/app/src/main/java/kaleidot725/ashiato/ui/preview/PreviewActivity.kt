package kaleidot725.ashiato.ui.preview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.PreviewActivityBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PreviewActivity : AppCompatActivity() {
    val previewViewModel: PreviewViewModel by viewModel()

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_activity)

        val binding =
            DataBindingUtil.setContentView<PreviewActivityBinding>(this, R.layout.preview_activity)
        binding?.lifecycleOwner = this
        binding?.viewmodel = previewViewModel

        viewPager = findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return previewViewModel.pageCount.value ?: 0
            }

            override fun getItem(position: Int): Fragment {
                return PageFragment.newInstance(position)
            }
        }
        viewPager.currentItem = previewViewModel.currentPage.value ?: 0
    }
}

