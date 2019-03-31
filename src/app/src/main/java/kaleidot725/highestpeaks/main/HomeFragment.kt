package kaleidot725.highestpeaks.main

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.HomeFragmentBinding
import kaleidot725.highestpeaks.service.LocationService
import java.lang.Exception

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var locationService: LocationService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        locationService = LocationService(context as Context)
        locationService.start()

        viewModel = ViewModelProviders.of(this, MainFragmentViewModelFactory()).get(MainViewModel::class.java)
        val binding = DataBindingUtil.bind<HomeFragmentBinding>(view)
        binding?.viewmodel =  viewModel
        binding?.lifecycleOwner = this

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        viewModel.dispose()
        locationService.stop()
        super.onDestroyView()
    }

    private inner class MainFragmentViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass == MainViewModel::class.java) {
                return MainViewModel(locationService) as  T
            }

            throw Exception("have created unknown class type")
        }
    }
}
