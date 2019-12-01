package kaleidot725.ashiato.ui.main.settinglist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.data.service.contact.Menu
import kaleidot725.ashiato.ui.main.MainNavigator

class MenuViewModelFactory(
    val context: Context,
    val navigaor: MainNavigator,
    val menu: Menu
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MenuViewModel::class.java) {
            return MenuViewModel(context, navigaor, menu) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}