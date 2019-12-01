package kaleidot725.ashiato.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import org.koin.android.viewmodel.ext.android.viewModel

class ContactFragment : Fragment() {

    companion object {
        fun newInstance() = ContactFragment()
    }

    val contactViewModel: ContactViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.contact_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.contact_recycler_view)
        contactViewModel.developers.observe(this, Observer {
            recyclerView.adapter = DeveloperAdapter(this, it)
            recyclerView.layoutManager = GridLayoutManager(context, 1)
            recyclerView.setHasFixedSize(true)
        })

        super.onViewCreated(view, savedInstanceState)
    }

}
