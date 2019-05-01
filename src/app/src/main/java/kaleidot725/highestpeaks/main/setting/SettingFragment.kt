package kaleidot725.highestpeaks.main.setting

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kaleidot725.highestpeaks.R

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private lateinit var viewModel: SettingViewModel
    private lateinit var menuList : List<Menu>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuList = listOf(Menu(R.drawable.ic_setting, "Setting"),
                          Menu(R.drawable.ic_license, "License"),
                          Menu(R.drawable.ic_contact, "Contact"))
        viewModel = ViewModelProviders.of(this, SettingViewModelFactory(menuList)).get(SettingViewModel::class.java)

        val recycler = view.findViewById<RecyclerView>(R.id.menu_recycler_view)
        recycler.adapter = MenuAdapter(this,  viewModel.menus)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
