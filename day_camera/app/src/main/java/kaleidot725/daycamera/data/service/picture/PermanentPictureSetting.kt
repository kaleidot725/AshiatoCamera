package kaleidot725.daycamera.data.service.picture

import kaleidot725.daycamera.data.permanent.PermanentJson

class PermanentPictureSetting(name: String) : PermanentJson<PictureSetting>(name, PictureSetting::class.java)
