package kaleidot725.ashiato.ui.main

import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable

class MainViewModel(navigator: MainNavigator) : ViewModel(), Disposable {

    private val navigator : MainNavigator = navigator
    private var disposed : Boolean = false

    val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            kaleidot725.ashiato.R.id.action_home -> {
                navigator.navigateHome()
                true
            }
            kaleidot725.ashiato.R.id.action_history -> {
                navigator.navigateHistory()
                true
            }
            kaleidot725.ashiato.R.id.action_setting -> {
                navigator.navigateSettingList()
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