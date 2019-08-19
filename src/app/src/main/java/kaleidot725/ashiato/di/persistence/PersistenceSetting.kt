package kaleidot725.ashiato.di.persistence

import kaleidot725.ashiato.di.data.Settings

class PersistenceSetting(name : String) : PersistenceJson<Settings>(name, Settings::class.java)




