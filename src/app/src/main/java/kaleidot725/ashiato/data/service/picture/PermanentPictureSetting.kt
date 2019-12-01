package kaleidot725.ashiato.data.service.picture

import kaleidot725.ashiato.data.permanent.PermanentJson

class PermanentPictureSetting(name: String) : PermanentJson<PictureSetting>(name, PictureSetting::class.java)
