package kaleidot725.ashiato.ui.contact

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.DeveloperRecyclerItemBinding

class DeveloperViewHolder(owner : LifecycleOwner, itemView: View, binding : DeveloperRecyclerItemBinding) : RecyclerView.ViewHolder(itemView) {
    private val binding : DeveloperRecyclerItemBinding = binding
    private val owner = owner
    private val itemView : View = itemView

    fun bind (vm : DeveloperViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
