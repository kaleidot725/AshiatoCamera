package kaleidot725.ashiato.ui.main

interface  MainNavigator {
    // Activity
    fun navigateCamera() : Boolean
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