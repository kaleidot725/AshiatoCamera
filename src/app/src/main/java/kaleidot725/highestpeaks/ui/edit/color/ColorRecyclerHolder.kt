package kaleidot725.highestpeaks.ui.edit.color

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.highestpeaks.databinding.ColorRecyclerItemBinding

class ColorRecyclerHolder(owner : LifecycleOwner, itemView : View, binding : ColorRecyclerItemBinding) : RecyclerView.ViewHolder(itemView) {
    private val binding : ColorRecyclerItemBinding = binding
    private val owner = owner

    fun bind(vm : ColorRecyclerViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}