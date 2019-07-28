package kaleidot725.highestpeaks.di.repository

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.*

interface DateTimeRepository {
    val running : Boolean

    val date : Subject<Date>
    val lastDate : Date

    fun start(intervalMs : Long)
    fun stop()
}