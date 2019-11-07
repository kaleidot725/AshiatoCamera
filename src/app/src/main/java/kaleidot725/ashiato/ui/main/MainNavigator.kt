package kaleidot725.ashiato.ui.main

import java.io.File

interface MainNavigator {
    // Activity
    fun navigateFolder(): Boolean

    fun navigateCamera(): Boolean

    fun navigateEdit(): Boolean
    fun navigateSetting(): Boolean
    fun navigateLicense(): Boolean
    fun navigateContact(): Boolean
    fun navigatePreview(): Boolean
    fun navigatePrivacy(): Boolean

    // Fragment
    fun navigateHome(): Boolean

    fun navigateHistory(): Boolean
    fun navigateSettingList(): Boolean

    // Share Compat
    fun navigateShare(files: List<File>)
}