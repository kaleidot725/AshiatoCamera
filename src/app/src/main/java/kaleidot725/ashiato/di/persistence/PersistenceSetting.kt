package kaleidot725.ashiato.di.persistence

import kaleidot725.ashiato.di.data.Settings
import kaleidot725.todo.model.persistence.PersistenceJson

class PersistenceSetting(name : String) : PersistenceJson<Settings>(name, Settings::class.java)




