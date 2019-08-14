package kaleidot725.ashiato.ui.main.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.PictureRecyclerItemBinding

class PictureAdapter(owner : LifecycleOwner, vms: List<PictureViewModelBase>) : RecyclerView.Adapter<PictureViewHolder>() {
    private val owner : LifecycleOwner = owner
    private val vms: MutableList<PictureViewModelBase> = vms.toMutableList()

    fun update(new : List<PictureViewModelBase>) {
        vms.clear()
        vms.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PictureRecyclerItemBinding>(inflater, R.layout.picture_recycler_item, parent, false)
        return PictureViewHolder(owner, binding.root, binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(vms[position])
    }

    override fun getItemCount(): Int = vms.count()
}