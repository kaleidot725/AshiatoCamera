package kaleidot725.daycamera.data.service.location

import kaleidot725.daycamera.data.permanent.PermanentJson

class PermanentLocationSetting(name: String) : PermanentJson<LocationSetting>(name, LocationSetting::class.java)
