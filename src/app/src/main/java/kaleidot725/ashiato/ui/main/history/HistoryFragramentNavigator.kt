package kaleidot725.ashiato.ui.main.history

import java.io.File

interface HistoryFragmentNavigator {
    fun navigateShare(files: List<File>)
    fun navigatePreview()
}