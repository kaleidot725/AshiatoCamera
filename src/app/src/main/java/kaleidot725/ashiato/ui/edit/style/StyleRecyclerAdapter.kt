package kaleidot725.ashiato.ui.edit.style

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.StyleRecyclerItemBinding

class StyleRecyclerAdapter(owner : LifecycleOwner, vms: List<StyleRecyclerViewModel>) : RecyclerView.Adapter<StyleRecyclerHolder>() {
    private val owner : LifecycleOwner = owner
    private val vms: MutableList<StyleRecyclerViewModel> = vms.toMutableList()

    fun update(new : List<StyleRecyclerViewModel>) {
        vms.clear()
        vms.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StyleRecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<StyleRecyclerItemBinding>(inflater, R.layout.style_recycler_item, parent, false)
        return StyleRecyclerHolder(owner, binding.root, binding)
    }

    override fun onBindViewHolder(holder: StyleRecyclerHolder, position: Int) {
        holder.bind(vms[position])
    }

    override fun getItemCount(): Int = vms.count()
}