package kaleidot725.ashiato.ui.main.settinglist

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.MenuRecyclerItemBinding

class MenuViewHolder(
    private val owner: LifecycleOwner,
    private val itemView: View,
    private val binding: MenuRecyclerItemBinding
) : RecyclerView.ViewHolder(itemView) {
    fun bind(vm: MenuViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
