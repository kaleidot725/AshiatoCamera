package kaleidot725.ashiato.ui.edit.position

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.PositionFragmentBindingImpl
import kaleidot725.ashiato.di.repository.PositionRepository
import kaleidot725.ashiato.di.service.picture.PictureEditor
import kaleidot725.ashiato.di.service.picture.PositionEditor
import javax.inject.Inject

class PositionFragment : Fragment() {

    companion object {
        fun newInstance() = PositionFragment()
    }

    @Inject
    lateinit var pictureEditor: PictureEditor

    @Inject
    lateinit var positionEditor : PositionEditor

    @Inject
    lateinit var positionRepository: PositionRepository

    private lateinit var viewModel: PositionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.position_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = PositionViewModelFactory(pictureEditor, positionEditor, positionRepository)
        viewModel = ViewModelProviders.of(this, factory).get(PositionViewModel::class.java)

        val binding = DataBindingUtil.bind<PositionFragmentBindingImpl>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel

        val recyclerView = this.view?.findViewById<(RecyclerView)>(R.id.position_recycler_view)
        recyclerView?.adapter = PositionRecyclerAdapter(this, viewModel.positionRecyclerViewModels.value ?: listOf())
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
