package kaleidot725.highestpeaks

data class Setting(val gpsProvider : String, val gpsMinTime : Int, val gpsMinDistance : Int)

fun loadSetting(filePath : String) : Setting {
    return Setting("", 0, 0)
}

fun saveSetting(filePath : String) {

}

