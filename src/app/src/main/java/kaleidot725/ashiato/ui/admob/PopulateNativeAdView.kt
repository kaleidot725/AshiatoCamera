package kaleidot725.ashiato.ui.admob

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.gms.ads.formats.UnifiedNativeAd


fun populateNativeAdView(nativeAd: UnifiedNativeAd, holder: UnifiedNativeAdViewHolder) {
    // Some assets are guaranteed to be in every UnifiedNativeAd.
    (holder.adView.headlineView as? TextView)?.text = nativeAd.headline
    (holder.adView.bodyView as? TextView)?.text = nativeAd.body
    (holder.adView.callToActionView as? Button)?.text = nativeAd.callToAction

    // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
    // check before trying to display them.
    val icon = nativeAd.icon

    if (icon == null) {
        holder.adView.iconView.visibility = View.INVISIBLE
    } else {
        (holder.adView.iconView as? ImageView)?.setImageDrawable(icon.drawable)
        holder.adView.iconView.visibility = View.VISIBLE
    }

    if (nativeAd.price == null) {
        holder.adView.priceView.visibility = View.INVISIBLE
    } else {
        holder.adView.priceView.visibility = View.VISIBLE
        (holder.adView.priceView as? TextView)?.text = nativeAd.price
    }

    if (nativeAd.store == null) {
        holder.adView.storeView.visibility = View.INVISIBLE
    } else {
        holder.adView.storeView.visibility = View.VISIBLE
        (holder.adView.storeView as? TextView)?.text = nativeAd.store
    }

    if (nativeAd.starRating == null) {
        holder.adView.starRatingView.visibility = View.INVISIBLE
    } else {
        (holder.adView.starRatingView as? RatingBar)?.rating = nativeAd.starRating.toFloat()
        holder.adView.starRatingView.visibility = View.VISIBLE
    }

    if (nativeAd.advertiser == null) {
        holder.adView.advertiserView.visibility = View.INVISIBLE
    } else {
        (holder.adView.advertiserView as? TextView)?.text = nativeAd.advertiser
        holder.adView.advertiserView.visibility = View.VISIBLE
    }

    // Assign native ad object to the native view.
    holder.adView.setNativeAd(nativeAd)
}