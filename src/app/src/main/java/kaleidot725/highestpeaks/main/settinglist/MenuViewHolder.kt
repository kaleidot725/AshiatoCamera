package kaleidot725.highestpeaks.main.settinglist

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.highestpeaks.databinding.MenuRecyclerItemBinding

class MenuViewHolder(owner : LifecycleOwner, itemView: View, binding : MenuRecyclerItemBinding) : RecyclerView.ViewHolder(itemView) {
    private val binding : MenuRecyclerItemBinding = binding
    private val owner = owner

    fun bind (vm : MenuViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
