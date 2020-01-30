package kaleidot725.ashiato.ui.edit.color

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.databinding.ColorRecyclerItemBinding

class ColorRecyclerHolder(
    private val owner: LifecycleOwner,
    private val itemView: View,
    private val binding: ColorRecyclerItemBinding
) : RecyclerView.ViewHolder(itemView) {

    fun bind(vm: ColorRecyclerViewModel?) {
        binding.vm = vm
        binding.executePendingBindings()
        binding.lifecycleOwner = owner
    }
}