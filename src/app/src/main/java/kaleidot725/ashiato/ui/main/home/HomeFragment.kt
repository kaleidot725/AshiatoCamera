package kaleidot725.ashiato.ui.main.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import dagger.android.support.AndroidSupportInjection
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.HomeFragmentBinding
import kaleidot725.ashiato.di.repository.DateTimeRepository
import kaleidot725.ashiato.di.repository.LocationRepository
import kaleidot725.ashiato.di.repository.PictureRepository
import kaleidot725.ashiato.ui.admob.UnifiedNativeAdViewHolder
import kaleidot725.ashiato.ui.admob.populateNativeAdView
import kaleidot725.ashiato.ui.main.MainNavigator
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var dateTimeRepository: DateTimeRepository

    @Inject
    lateinit var pictureRepository: PictureRepository

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProviders.of(this, MainFragmentViewModelFactory())
            .get(HomeViewModel::class.java)
        val binding = DataBindingUtil.bind<HomeFragmentBinding>(view)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = this

        loadAdvertisement(view.findViewById(R.id.ad_container))
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadAdvertisement(view: FrameLayout) {
        try {
            val appInfo = this.context?.packageManager?.getApplicationInfo(
                this.context?.packageName,
                PackageManager.GET_META_DATA
            )
            val adId = appInfo?.metaData?.getString("ASHIATO_ADMOB_AD_ID")

            val builder = AdLoader.Builder(this.context, adId)
            builder.forUnifiedNativeAd {
                val adView = layoutInflater.inflate(R.layout.unified_native_adview, null)
                populateNativeAdView(it, UnifiedNativeAdViewHolder(adView))

                view.removeAllViews()
                view.addView(adView)
            }

            val loader = builder.build()
            loader.loadAd(AdRequest.Builder().build())
        } catch (e: Exception) {
            Log.v("HomeFragment", e.toString())
        }
    }

    @Suppress("UNCHECKED_CAST")
    private inner class MainFragmentViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass == HomeViewModel::class.java) {
                return HomeViewModel(
                    navigator,
                    dateTimeRepository,
                    locationRepository,
                    pictureRepository
                ) as T
            }

            throw Exception("have created unknown class type")
        }
    }
}
