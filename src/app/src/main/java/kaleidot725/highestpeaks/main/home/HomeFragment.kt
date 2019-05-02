package kaleidot725.highestpeaks.main.home

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.MyApplicationNavigator
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.HomeFragmentBinding
import kaleidot725.highestpeaks.camera.CameraActivity
import kaleidot725.highestpeaks.model.service.LocationService
import java.lang.Exception
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var navigator : MyApplicationNavigator

    @Inject
    lateinit var locationService: LocationService

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProviders.of(this, MainFragmentViewModelFactory()).get(HomeViewModel::class.java)
        val binding = DataBindingUtil.bind<HomeFragmentBinding>(view)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = this

        val cameraFab: FloatingActionButton = view.findViewById(R.id.cameraFab)
        cameraFab.setOnClickListener { navigator.navigateCamera() }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        viewModel.dispose()
        super.onDestroyView()
    }

    private inner class MainFragmentViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass == HomeViewModel::class.java) {
                return HomeViewModel(locationService) as  T
            }

            throw Exception("have created unknown class type")
        }
    }
}
