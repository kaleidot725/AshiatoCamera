package kaleidot725.highestpeaks.ui.preview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.di.repository.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.ui.main.history.HistoryFragment
import kaleidot725.michetimer.model.repository.PictureRepository
import kotlinx.android.synthetic.main.preview_activity.*
import javax.inject.Inject
import javax.inject.Named

class PreviewActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var repository : PictureRepository

    @Inject @field:Named("SelectedPicture")
    lateinit var selected : Holder<Picture>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_activity)

        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        val all = repository.all()
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return repository.count()
            }

            override fun getItem(position: Int): Fragment {
                return PreviewFragment.newInstance(position)
            }
        }
        viewPager.currentItem = all.indexOf(selected.lastedValue)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}

