package kaleidot725.daycamera.data.repository

import android.content.Context
import androidx.databinding.ObservableArrayList
import kaleidot725.daycamera.R
import kaleidot725.daycamera.data.service.contact.Menu

class MenuRepositoryImpl(val context: Context) : MenuRepository {
    private val list: ArrayList<Menu> = ObservableArrayList<Menu>()
    private var initialized: Boolean = false

    init {
        list.add(
            Menu(
                R.drawable.ic_setting,
                context.getString(R.string.menu_setting)
            )
        )
        list.add(
            Menu(
                R.drawable.ic_license,
                context.getString(R.string.menu_license)
            )
        )
        list.add(
            Menu(
                R.drawable.ic_contact,
                context.getString(R.string.menu_contanct)
            )
        )
        list.add(
            Menu(
                R.drawable.ic_privacy_policy,
                context.getString(R.string.menu_privacy)
            )
        )
        initialized = true
    }

    override fun all(): List<Menu> {
        if (!initialized) {
            throw IllegalStateException("not initialized")
        }

        return this.list.toList()
    }

    override fun count(): Int {
        if (!initialized) {
            throw IllegalStateException("not initialized")
        }

        return this.list.count()
    }
}