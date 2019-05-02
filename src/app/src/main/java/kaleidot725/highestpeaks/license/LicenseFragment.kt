package kaleidot725.highestpeaks.license

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.R

class LicenseFragment : Fragment() {

    companion object {
        fun newInstance() = LicenseFragment()
    }

    private lateinit var viewModel: LicenseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.license_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LicenseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
