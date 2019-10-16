package kaleidot725.ashiato.ui.admob

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView


fun populateNativeAdView(nativeAd: UnifiedNativeAd, adView: UnifiedNativeAdView) {
    // Some assets are guaranteed to be in every UnifiedNativeAd.
    (adView.headlineView as TextView).text = nativeAd.headline
    (adView.bodyView as TextView).text = nativeAd.body
    (adView.callToActionView as Button).setText(nativeAd.callToAction)

    // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
    // check before trying to display them.
    val icon = nativeAd.icon

    if (icon == null) {
        adView.iconView.visibility = View.INVISIBLE
    } else {
        (adView.iconView as ImageView).setImageDrawable(icon.drawable)
        adView.iconView.visibility = View.VISIBLE
    }

    if (nativeAd.price == null) {
        adView.priceView.visibility = View.INVISIBLE
    } else {
        adView.priceView.visibility = View.VISIBLE
        (adView.priceView as TextView).text = nativeAd.price
    }

    if (nativeAd.store == null) {
        adView.storeView.visibility = View.INVISIBLE
    } else {
        adView.storeView.visibility = View.VISIBLE
        (adView.storeView as TextView).text = nativeAd.store
    }

    if (nativeAd.starRating == null) {
        adView.starRatingView.visibility = View.INVISIBLE
    } else {
        (adView.starRatingView as RatingBar).rating = nativeAd.starRating.toFloat()
        adView.starRatingView.visibility = View.VISIBLE
    }

    if (nativeAd.advertiser == null) {
        adView.advertiserView.visibility = View.INVISIBLE
    } else {
        (adView.advertiserView as TextView).text = nativeAd.advertiser
        adView.advertiserView.visibility = View.VISIBLE
    }

    // Assign native ad object to the native view.
    adView.setNativeAd(nativeAd)
}