package kaleidot725.ashiato.ui.main

interface MainNavigator {
    fun navigateFolder(): Boolean

    fun navigateCamera(): Boolean

    fun navigateEdit(): Boolean

    fun navigateHome(): Boolean

    fun navigateHistory(): Boolean
    
    fun navigateSettingList(): Boolean
}