package kaleidot725.highestpeaks.ui.preview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.PreviewActivityBinding
import kaleidot725.highestpeaks.di.holder.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository
import javax.inject.Inject
import javax.inject.Named

class PreviewActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var repository : PictureRepository

    private lateinit var viewModel: PreviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_activity)

        val factory = PreviewViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(PreviewViewModel::class.java)

        val binding = DataBindingUtil.setContentView<PreviewActivityBinding>(this, R.layout.preview_activity)
        binding?.lifecycleOwner = this
        binding?.viewmodel = viewModel

        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return viewModel.pageCount.value ?: 0
            }

            override fun getItem(position: Int): Fragment {
                return PageFragment.newInstance(position)
            }
        }
        viewPager.currentItem = viewModel.currentPage.value ?: 0
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}

