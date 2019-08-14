package kaleidot725.ashiato.ui.main.history

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.PictureRecyclerItemBinding

class PictureViewHolder(owner : LifecycleOwner, itemView: View, binding : PictureRecyclerItemBinding) : RecyclerView.ViewHolder(itemView) {
    private val binding : PictureRecyclerItemBinding = binding
    private val owner = owner

    fun bind (vm : PictureViewModelBase?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
