package kaleidot725.ashiato.ui.edit.rotation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.RotationRecyclerItemBinding

class RotationRecyclerAdapter(
    private val owner: LifecycleOwner,
    vms: List<RotationRecyclerViewModel>
) : RecyclerView.Adapter<RotationRecyclerHolder>() {
    private val vms: MutableList<RotationRecyclerViewModel> = vms.toMutableList()

    fun update(new: List<RotationRecyclerViewModel>) {
        vms.clear()
        vms.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RotationRecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RotationRecyclerItemBinding>(
            inflater,
            R.layout.rotation_recycler_item,
            parent,
            false
        )
        return RotationRecyclerHolder(owner, binding.root, binding)
    }

    override fun onBindViewHolder(holder: RotationRecyclerHolder, position: Int) {
        holder.bind(vms[position])
    }

    override fun getItemCount() = vms.count()
}
