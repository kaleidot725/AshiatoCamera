package kaleidot725.ashiato.ui.edit.rotation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.RotationFragmentBindingImpl
import kaleidot725.ashiato.data.repository.AngleRepository
import kaleidot725.ashiato.data.service.picture.PictureEditor
import kaleidot725.ashiato.data.service.picture.RotationEditor
import javax.inject.Inject

class RotationFragment : Fragment() {

    companion object {
        fun newInstance() = RotationFragment()
    }

    @Inject
    lateinit var pictureEditor: PictureEditor

    @Inject
    lateinit var rotationEditor: RotationEditor

    @Inject
    lateinit var angleRepository: AngleRepository

    private lateinit var viewModel: RotationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.rotation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = RotationViewModelFactory(pictureEditor, rotationEditor, angleRepository)
        viewModel = ViewModelProviders.of(this, factory).get(RotationViewModel::class.java)

        val binding = DataBindingUtil.bind<RotationFragmentBindingImpl>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel

        val recyclerView = this.view?.findViewById<RecyclerView>(R.id.rotation_recycler_view)
        viewModel.rotaionRecyclerViewModels.observe(this, Observer {
            recyclerView?.adapter =
                RotationRecyclerAdapter(this, viewModel.rotaionRecyclerViewModels.value ?: listOf())
            recyclerView?.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView?.setHasFixedSize(true)
        })

        viewModel.load()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
