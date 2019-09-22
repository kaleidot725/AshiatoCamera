package kaleidot725.ashiato.di.service.picture

import kaleidot725.ashiato.di.permanent.PermanentJson

class PermanentPictureSetting(name: String) : PermanentJson<PictureSetting>(name, PictureSetting::class.java)
