package kaleidot725.ashiato.ui.contact

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.DeveloperRecyclerItemBinding

class DeveloperViewHolder(
    private val owner: LifecycleOwner,
    private val itemView: View,
    private val binding: DeveloperRecyclerItemBinding
) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(vm: DeveloperViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
