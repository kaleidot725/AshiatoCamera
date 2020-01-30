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
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.HomeFragmentBinding
import kaleidot725.ashiato.ui.admob.UnifiedNativeAdViewHolder
import kaleidot725.ashiato.ui.admob.populateNativeAdView
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<HomeFragmentBinding>(inflater, R.layout.home_fragment, container, false).apply {
            viewmodel = homeViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
                val adView = activity?.layoutInflater?.inflate(R.layout.unified_native_adview, null)
                if (adView != null) {
                    populateNativeAdView(it, UnifiedNativeAdViewHolder(adView))
                    view.removeAllViews()
                    view.addView(adView)
                }
            }

            val loader = builder.build()
            loader.loadAd(AdRequest.Builder().build())
        } catch (e: Exception) {
            Log.v("HomeFragment", e.toString())
        }
    }
}
