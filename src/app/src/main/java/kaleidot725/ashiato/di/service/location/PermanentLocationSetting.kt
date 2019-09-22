package kaleidot725.ashiato.di.service.location

import kaleidot725.ashiato.di.permanent.PermanentJson

class PermanentLocationSetting(name: String) : PermanentJson<LocationSetting>(name, LocationSetting::class.java)
