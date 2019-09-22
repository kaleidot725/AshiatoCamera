package kaleidot725.ashiato.ui.main.settinglist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import kaleidot725.ashiato.R
import kaleidot725.ashiato.di.repository.MenuRepository
import kaleidot725.ashiato.ui.main.MainNavigator
import javax.inject.Inject

class SettingListFragment : Fragment() {

    companion object {
        fun newInstance() = SettingListFragment()
    }

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var repository: MenuRepository

    private lateinit var listViewModel: SettingListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.settinglist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = SettingListViewModelFactory(this.context as Context, navigator, repository.all())
        listViewModel = ViewModelProviders.of(this, factory).get(SettingListViewModel::class.java)

        val recycler = view.findViewById<RecyclerView>(R.id.menu_recycler_view)
        recycler.adapter = MenuAdapter(this, listViewModel.menus)
        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.setHasFixedSize(true)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
