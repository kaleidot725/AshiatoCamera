package kaleidot725.highestpeaks.model.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.highestpeaks.R
import java.lang.IllegalStateException

class DeveloperRepositoryImpl : DeveloperRepository {
    private val list : ArrayList<Developer> = ObservableArrayList<Developer>()
    private var initialized : Boolean = false

    override fun init() {
        list.add(
            Developer(
            "Yusuke Katsuragawa",
            "https://github.com/kaleidot725",
            "https://twitter.com/kaleidot725",
             "hakodate.katsuragawa.yusuke@gmail.com"
            )
        )
        initialized = true
    }

    override fun all(): List<Developer> {
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