package kaleidot725.highestpeaks.main.settinglist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Binds
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.MyApplicationNavigator

import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.main.MainNavigator
import kaleidot725.highestpeaks.model.repository.DefaultMenuRepository
import kaleidot725.highestpeaks.model.repository.MenuRepository
import javax.inject.Inject

class SettingListFragment : Fragment() {

    companion object {
        fun newInstance() = SettingListFragment()
    }

    @Inject
    lateinit var navigator : MyApplicationNavigator

    @Inject
    lateinit var repository : MenuRepository

    private lateinit var listViewModel: SettingListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.setting_menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listViewModel = ViewModelProviders.of(this, SettingListViewModelFactory(repository.all())).get(SettingListViewModel::class.java)

        val recycler = view.findViewById<RecyclerView>(R.id.menu_recycler_view)
        recycler.adapter = MenuAdapter(this,  listViewModel.menus)
        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.setHasFixedSize(true)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
