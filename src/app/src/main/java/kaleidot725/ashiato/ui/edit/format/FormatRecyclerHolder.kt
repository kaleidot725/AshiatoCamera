package kaleidot725.ashiato.ui.edit.format

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.FormatRecyclerItemBinding

class FormatRecyclerHolder(
    private val owner: LifecycleOwner,
    private val itemView: View,
    private val binding: FormatRecyclerItemBinding
) : RecyclerView.ViewHolder(itemView) {

    fun bind(vm: FormatRecyclerViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
