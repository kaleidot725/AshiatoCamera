package kaleidot725.highestpeaks.main

import androidx.lifecycle.ViewModel
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable

class MainViewModel(navigator: MainNavigator) : ViewModel(), Disposable {

    val navigator : MainNavigator = navigator
    var disposed : Boolean = false

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            kaleidot725.highestpeaks.R.id.action_home -> {
                return navigator.navigateHome()
            }
            kaleidot725.highestpeaks.R.id.action_history -> {
                return navigator.navigateHistory()
            }
            kaleidot725.highestpeaks.R.id.action_setting -> {
                return navigator.navigateSetting()
            }
        }

        return false
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