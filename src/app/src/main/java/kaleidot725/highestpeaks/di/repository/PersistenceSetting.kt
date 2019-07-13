package kaleidot725.highestpeaks.di.repository

import kaleidot725.highestpeaks.di.data.Setting
import kaleidot725.todo.model.persistence.PersistenceJson

class PersistenceSetting(name : String) : PersistenceJson<Setting>(name, Setting::class.java)




