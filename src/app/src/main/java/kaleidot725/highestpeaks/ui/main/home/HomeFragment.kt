package kaleidot725.highestpeaks.ui.main.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.HomeFragmentBinding
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.michetimer.model.repository.PictureRepository
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var navigator : MainNavigator

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var pictureRepository : PictureRepository

    @Inject @field:Named("EditPicture")
    lateinit var editPicture : Holder<Picture>

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, MainFragmentViewModelFactory()).get(HomeViewModel::class.java)
        val binding = DataBindingUtil.bind<HomeFragmentBinding>(view)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = this

        val cameraFab: Button = view.findViewById(R.id.cameraFab)
        cameraFab.setOnClickListener {
            editPicture.value = pictureRepository.newPicture()
            navigator.navigateCamera(editPicture.value)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private inner class MainFragmentViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass == HomeViewModel::class.java) {
                return HomeViewModel(locationRepository) as  T
            }

            throw Exception("have created unknown class type")
        }
    }
}
