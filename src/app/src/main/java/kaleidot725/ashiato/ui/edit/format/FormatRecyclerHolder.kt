package kaleidot725.ashiato.ui.edit.format

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.FormatRecyclerItemBinding

class FormatRecyclerHolder(owner: LifecycleOwner, itemView: View, binding: FormatRecyclerItemBinding) :
    RecyclerView.ViewHolder(itemView) {
    private val binding: FormatRecyclerItemBinding = binding
    private val owner = owner

    fun bind(vm: FormatRecyclerViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
