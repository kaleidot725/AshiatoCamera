package kaleidot725.ashiato.ui.privacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import kaleidot725.ashiato.R

class PrivacyFragment : Fragment() {

    companion object {
        fun newInstance() = PrivacyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.privacy_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val webView = view.findViewById<WebView>(R.id.webview)
        webView.loadUrl(getString(R.string.privacy_policy_url))
        webView.settings.textZoom = 65

        super.onViewCreated(view, savedInstanceState)
    }
}
