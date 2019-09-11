package kaleidot725.ashiato.di.persistence

import kaleidot725.ashiato.di.service.location.Settings

class PersistenceSetting(name: String) : PersistenceJson<Settings>(name, Settings::class.java)




