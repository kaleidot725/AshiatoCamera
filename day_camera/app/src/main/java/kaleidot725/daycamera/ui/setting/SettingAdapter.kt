package kaleidot725.daycamera.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.daycamera.R
import kaleidot725.daycamera.data.service.contact.Menu
import kaleidot725.daycamera.data.service.contact.MenuType

class SettingAdapter : RecyclerView.Adapter<SettingViewHolder>() {
    var onClick: (t: MenuType) -> (Unit) = {}

    private val menus: MutableList<Menu> = mutableListOf()

    fun update(new: List<Menu>) {
        menus.clear()
        menus.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_setting_menu_item, parent, false)
        return SettingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onClick(menus[position].type) }
        holder.bind(menus[position])
    }

    override fun getItemCount(): Int = menus.count()
}