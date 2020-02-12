package kaleidot725.daycamera.data.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.daycamera.data.service.contact.Developer

class DeveloperRepositoryImpl : DeveloperRepository {
    private val list: ArrayList<Developer> = ObservableArrayList<Developer>()

    init {
        list.add(
            Developer(
                "Yusuke Katsuragawa",
                "https://github.com/kaleidot725",
                "https://twitter.com/kaleidot725",
                "hakodate.katsuragawa.yusuke@gmail.com"
            )
        )
    }

    override fun all(): List<Developer> {
        return this.list.toList()
    }

    override fun count(): Int {
        return this.list.count()
    }
}