package kaleidot725.highestpeaks.model.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.model.data.Menu
import java.lang.IllegalStateException

class MenuRepositoryImpl : MenuRepository {
    private val list : ArrayList<Menu> = ObservableArrayList<Menu>()
    private var initialized : Boolean = false

    override fun init() {
        list.add(Menu(R.drawable.ic_setting, "Setting"))
        list.add(Menu(R.drawable.ic_license, "License"))
        list.add(Menu(R.drawable.ic_contact, "Contact"))
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