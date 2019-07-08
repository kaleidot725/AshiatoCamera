package kaleidot725.highestpeaks.main

import kaleidot725.highestpeaks.model.data.Picture
import java.io.File

interface  MainNavigator {
    // Activity
    fun navigateCamera(picture : Picture) : Boolean
    fun navigateEdit() : Boolean
    fun navigateSetting() : Boolean
    fun navigateLicense() : Boolean
    fun navigateContact() : Boolean
    fun navigatePreview() : Boolean

    // Fragment
    fun navigateHome() : Boolean
    fun navigateHistory() : Boolean
    fun navigateSettingList() : Boolean
}