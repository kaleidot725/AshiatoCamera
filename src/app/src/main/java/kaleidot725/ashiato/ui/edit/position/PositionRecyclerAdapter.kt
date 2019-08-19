package kaleidot725.ashiato.ui.edit.position

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.PositionRecyclerItemBinding

class PositionRecyclerAdapter(owner: LifecycleOwner, vms: List<PositionRecyclerViewModel>) :
    RecyclerView.Adapter<PositionRecyclerHolder>() {

    private val owner: LifecycleOwner = owner
    private val vms: MutableList<PositionRecyclerViewModel> = vms.toMutableList()

    fun update(new: List<PositionRecyclerViewModel>) {
        vms.clear()
        vms.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionRecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PositionRecyclerItemBinding>(
            inflater,
            R.layout.position_recycler_item,
            parent,
            false
        )
        return PositionRecyclerHolder(owner, binding.root, binding)
    }

    override fun onBindViewHolder(holder: PositionRecyclerHolder, position: Int) {
        holder.bind(vms[position])
    }

    override fun getItemCount(): Int = vms.count()
}