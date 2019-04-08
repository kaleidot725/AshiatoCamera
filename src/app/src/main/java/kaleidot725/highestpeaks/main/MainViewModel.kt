package kaleidot725.highestpeaks.main

import androidx.lifecycle.ViewModel
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable

class MainViewModel(navigator: MainNavigator) : ViewModel(), Disposable {

    val navigator : MainNavigator = navigator
    var disposed : Boolean = false

    val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            kaleidot725.highestpeaks.R.id.action_home -> {
                navigator.navigateHome()
                true
            }
            kaleidot725.highestpeaks.R.id.action_history -> {
                navigator.navigateHistory()
                true
            }
            kaleidot725.highestpeaks.R.id.action_setting -> {
                navigator.navigateSetting()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun dispose() {
        if (disposed) {
            throw IllegalStateException("already disposed")
        }

        disposed = true
    }

    override fun isDisposed(): Boolean {
        return disposed
    }
}