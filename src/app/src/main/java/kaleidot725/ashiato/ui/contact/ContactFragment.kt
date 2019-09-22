package kaleidot725.ashiato.ui.contact

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
import kaleidot725.ashiato.di.repository.DeveloperRepository
import javax.inject.Inject

class ContactFragment : Fragment() {

    companion object {
        fun newInstance() = ContactFragment()
    }

    @Inject
    lateinit var developerRepository: DeveloperRepository

    private lateinit var viewModel: ContactViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.contact_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel =
            ViewModelProviders.of(this, ContactViewModelFactory(developerRepository)).get(ContactViewModel::class.java)
        val recyclerView = view.findViewById<RecyclerView>(R.id.contact_recycler_view)
        recyclerView.adapter = DeveloperAdapter(this, viewModel.developers)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.setHasFixedSize(true)
        super.onViewCreated(view, savedInstanceState)
    }

}
