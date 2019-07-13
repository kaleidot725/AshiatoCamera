package kaleidot725.highestpeaks.ui.main

import kaleidot725.highestpeaks.di.data.Picture

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