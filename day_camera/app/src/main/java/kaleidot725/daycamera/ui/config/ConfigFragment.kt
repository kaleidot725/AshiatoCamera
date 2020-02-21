package kaleidot725.daycamera.ui.config


import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import kaleidot725.daycamera.R

class ConfigFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}
