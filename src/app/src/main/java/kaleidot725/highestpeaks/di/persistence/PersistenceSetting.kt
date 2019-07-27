package kaleidot725.highestpeaks.di.persistence

import kaleidot725.highestpeaks.di.data.Settings
import kaleidot725.todo.model.persistence.PersistenceJson

class PersistenceSetting(name : String) : PersistenceJson<Settings>(name, Settings::class.java)




