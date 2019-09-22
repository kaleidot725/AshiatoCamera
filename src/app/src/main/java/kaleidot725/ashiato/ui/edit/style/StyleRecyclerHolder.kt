package kaleidot725.ashiato.ui.edit.style

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.StyleRecyclerItemBinding

class StyleRecyclerHolder(owner: LifecycleOwner, itemView: View, binding: StyleRecyclerItemBinding) :
    RecyclerView.ViewHolder(itemView) {
    private val binding: StyleRecyclerItemBinding = binding
    private val owner = owner

    fun bind(vm: StyleRecyclerViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
