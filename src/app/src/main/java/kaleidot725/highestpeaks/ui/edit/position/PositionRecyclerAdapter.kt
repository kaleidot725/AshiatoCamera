package kaleidot725.highestpeaks.ui.edit.position

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.PositionRecyclerItemBinding
import kotlinx.android.synthetic.main.position_fragment.view.*

class PositionRecyclerAdapter(owner : LifecycleOwner, vms : List<PositionRecyclerViewModel>) : RecyclerView.Adapter<PositionRecyclerHolder>() {

    private val owner : LifecycleOwner = owner
    private val vms : MutableList<PositionRecyclerViewModel> = vms.toMutableList()

    fun update(new : List<PositionRecyclerViewModel>) {
        vms.clear()
        vms.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionRecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PositionRecyclerItemBinding>(inflater, R.layout.position_recycler_item, parent, false)
        return PositionRecyclerHolder(owner, binding.root, binding)
    }

    override fun onBindViewHolder(holder: PositionRecyclerHolder, position: Int) {
        holder.bind(vms[position])
    }

    override fun getItemCount(): Int = vms?.count() ?: 0
}