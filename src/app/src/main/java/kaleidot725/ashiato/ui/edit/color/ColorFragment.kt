package kaleidot725.ashiato.ui.edit.color

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
import kaleidot725.ashiato.databinding.ColorFragmentBindingImpl
import kaleidot725.ashiato.data.repository.ColorRepository
import kaleidot725.ashiato.data.service.picture.ColorEditor
import kaleidot725.ashiato.data.service.picture.PictureEditor
import javax.inject.Inject

class ColorFragment : Fragment() {

    companion object {
        fun newInstance() = ColorFragment()
    }

    @Inject
    lateinit var pictureEditor: PictureEditor

    @Inject
    lateinit var colorEditor: ColorEditor

    @Inject
    lateinit var repository: ColorRepository

    private lateinit var viewModel: ColorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.color_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            ColorViewModelFactory(pictureEditor, colorEditor, repository)
        )
            .get(ColorViewModel::class.java)

        val binding = DataBindingUtil.bind<ColorFragmentBindingImpl>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel

        val recyclerView = this.view?.findViewById<RecyclerView>(R.id.color_recycler_View)
        viewModel.colorRecyclerViewModels.observe(this, Observer {
            recyclerView?.adapter =
                ColorRecyclerAdapter(this, viewModel.colorRecyclerViewModels.value ?: listOf())
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
