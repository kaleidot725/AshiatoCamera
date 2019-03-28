package kaleidot725.highestpeaks.main

import android.content.Context
import android.location.Location
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
import kaleidot725.highestpeaks.databinding.MainFragmentBinding
import kaleidot725.highestpeaks.service.LocationService
import java.lang.Exception

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var locationService: LocationService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // model
        locationService = LocationService(context as Context)
        locationService.start()

        // viewmodel
        viewModel = ViewModelProviders.of(this, MainFragmentViewModelFactory()).get(MainViewModel::class.java)
        val binding = DataBindingUtil.bind<MainFragmentBinding>(view)
        binding?.viewmodel =  viewModel
        binding?.lifecycleOwner = this

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        // viewmodel
        viewModel.dispose()

        // model
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
