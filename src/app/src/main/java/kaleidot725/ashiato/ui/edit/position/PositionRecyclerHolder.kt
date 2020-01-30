package kaleidot725.ashiato.ui.edit.position

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.PositionRecyclerItemBinding

class PositionRecyclerHolder(
    private val owner: LifecycleOwner,
    private val itemView: View,
    private val binding: PositionRecyclerItemBinding
) : RecyclerView.ViewHolder(itemView) {
    fun bind(vm: PositionRecyclerViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
