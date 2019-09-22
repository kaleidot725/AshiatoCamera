package kaleidot725.ashiato.ui.edit

interface EditNavigator {
    fun navigateFormatEditor(): Boolean
    fun navigateStyleEditor(): Boolean
    fun navigateColorEditor(): Boolean
    fun navigatePositionEditor(): Boolean
    fun navigateRotationEditor(): Boolean
    fun exit(): Boolean
}