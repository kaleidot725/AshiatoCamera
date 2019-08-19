package kaleidot725.ashiato.ui.main.settinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.MenuRecyclerItemBinding

class MenuAdapter(owner: LifecycleOwner, vms: List<MenuViewModel>) : RecyclerView.Adapter<MenuViewHolder>() {
    private val owner: LifecycleOwner = owner
    private val vms: MutableList<MenuViewModel> = vms.toMutableList()

    fun update(new: List<MenuViewModel>) {
        vms.clear()
        vms.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<MenuRecyclerItemBinding>(inflater, R.layout.menu_recycler_item, parent, false)
        return MenuViewHolder(owner, binding.root, binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(vms[position])
    }

    override fun getItemCount(): Int = vms.count()
}