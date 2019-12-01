package kaleidot725.ashiato.data.service.location

import kaleidot725.ashiato.data.permanent.PermanentJson

class PermanentLocationSetting(name: String) : PermanentJson<LocationSetting>(name, LocationSetting::class.java)
