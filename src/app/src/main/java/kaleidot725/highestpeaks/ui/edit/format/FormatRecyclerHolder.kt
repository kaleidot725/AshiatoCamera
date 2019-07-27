package kaleidot725.highestpeaks.ui.edit.format

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.highestpeaks.databinding.FormatRecyclerItemBinding
import kaleidot725.highestpeaks.databinding.PictureRecyclerItemBinding
import kaleidot725.highestpeaks.ui.main.history.PictureViewModelBase

class FormatRecyclerHolder(owner : LifecycleOwner, itemView: View, binding : FormatRecyclerItemBinding) : RecyclerView.ViewHolder(itemView) {
    private val binding : FormatRecyclerItemBinding = binding
    private val owner = owner

    fun bind (vm : FormatRecyclerViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}
