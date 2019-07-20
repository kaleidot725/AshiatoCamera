package kaleidot725.highestpeaks.ui.preview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewParent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
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

        if (savedInstanceState == null) {
            val viewPager = findViewById<ViewPager>(R.id.viewpager)
            val all = repository.all()
//            viewpager.currentItem = all.indexOf(selected.value)
            viewpager.adapter = PreviewFragmentAdapter(repository, selected, supportFragmentManager)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    inner class PreviewFragmentAdapter(val repository : PictureRepository, val selected : Holder<Picture>, val fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        private val list = repository.all()

        override fun getItem(position: Int): Fragment {
            this@PreviewActivity.title = list[position].name
            selected.value = list[position]
            return PreviewFragment.newInstance()
        }

        override fun getCount(): Int {
            return list.count()
        }
    }
}

