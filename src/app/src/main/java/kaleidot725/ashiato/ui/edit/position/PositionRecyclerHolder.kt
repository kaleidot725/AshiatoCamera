package kaleidot725.ashiato.ui.edit.position

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.PositionRecyclerItemBinding

class PositionRecyclerHolder(
    owner : LifecycleOwner,
    itemView : View, binding :
    PositionRecyclerItemBinding
) : RecyclerView.ViewHolder(itemView)
{
    private val binding : PositionRecyclerItemBinding = binding
    private val owner = owner

    fun bind(vm :  PositionRecyclerViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}