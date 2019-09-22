package kaleidot725.ashiato.ui.edit.format

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.FormatRecyclerItemBinding

class FormatRecyclerAdapter(owner: LifecycleOwner, vms: List<FormatRecyclerViewModel>) :
    RecyclerView.Adapter<FormatRecyclerHolder>() {
    private val owner: LifecycleOwner = owner
    private val vms: MutableList<FormatRecyclerViewModel> = vms.toMutableList()

    fun update(new: List<FormatRecyclerViewModel>) {
        vms.clear()
        vms.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormatRecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<FormatRecyclerItemBinding>(inflater, R.layout.format_recycler_item, parent, false)
        return FormatRecyclerHolder(owner, binding.root, binding)
    }

    override fun onBindViewHolder(holder: FormatRecyclerHolder, position: Int) {
        holder.bind(vms[position])
    }

    override fun getItemCount(): Int = vms.count()
}