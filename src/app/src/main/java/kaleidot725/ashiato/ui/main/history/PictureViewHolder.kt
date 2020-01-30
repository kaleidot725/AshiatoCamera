package kaleidot725.ashiato.ui.main.history

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.PictureRecyclerItemBinding

class PictureViewHolder(
    private val owner: LifecycleOwner,
    private val itemView: View,
    private val binding: PictureRecyclerItemBinding
) : RecyclerView.ViewHolder(itemView) {
    fun bind(vm: PictureViewModelBase?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
