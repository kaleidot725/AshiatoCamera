package kaleidot725.ashiato.ui.edit.rotation

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.PositionRecyclerItemBinding
import kaleidot725.ashiato.databinding.RotationRecyclerItemBinding
import kaleidot725.ashiato.ui.edit.position.PositionRecyclerViewModel

class RotationRecyclerHolder(
    private val owner: LifecycleOwner,
    private val itemView: View,
    private val binding : RotationRecyclerItemBinding
) : RecyclerView.ViewHolder(itemView)
{
    fun bind(vm : RotationRecyclerViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}