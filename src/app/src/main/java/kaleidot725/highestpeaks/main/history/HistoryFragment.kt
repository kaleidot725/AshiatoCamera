package kaleidot725.highestpeaks.main.history

import android.graphics.Picture
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.HistoryFragmentBinding
import kaleidot725.michetimer.model.repository.DefaultPictureRepository
import kaleidot725.michetimer.model.repository.PictureRepository

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private lateinit var viewModel: HistoryViewModel
    private lateinit var repository : PictureRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FIXME
        val dcimPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val dirPath = "${dcimPath}/Highest-Peak"
        repository = DefaultPictureRepository(dirPath).also { it.init() }

        viewModel = ViewModelProviders.of(this, HistoryViewModelFactory(repository)).get(HistoryViewModel::class.java)
        val binding = DataBindingUtil.bind<HistoryFragmentBinding>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel

        val recyclerView =view.findViewById<RecyclerView>(R.id.picture_recycler_view)
        recyclerView.adapter = PictureAdapter(this, viewModel.pictureViewModels.value ?: listOf())
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.setHasFixedSize(true)
    }
}
