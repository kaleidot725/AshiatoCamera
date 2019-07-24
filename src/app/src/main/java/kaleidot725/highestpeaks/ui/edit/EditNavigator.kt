package kaleidot725.highestpeaks.ui.edit

interface EditNavigator {
    fun navigateFormatEditor() : Boolean
    fun navigateColorEditor() : Boolean
    fun navigatePositionEditor() : Boolean
    fun navigateRotationEditor() : Boolean
    fun exit() : Boolean
}