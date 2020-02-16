package kaleidot725.daycamera.ui.setting

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.daycamera.R
import kaleidot725.daycamera.data.service.contact.Menu

class SettingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val iconImageView: ImageView = itemView.findViewById(R.id.icon_image_view)
    private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
    private val subTitleTextView: TextView = itemView.findViewById(R.id.sub_title_text_view)

    fun bind(menu: Menu) {
        iconImageView.setImageResource(menu.icon)
        titleTextView.text = menu.title
        subTitleTextView.text = menu.subTitle
    }
}
