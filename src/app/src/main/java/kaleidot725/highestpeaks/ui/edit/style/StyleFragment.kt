package kaleidot725.highestpeaks.ui.edit.style

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection

import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.FormatFragmentBindingImpl
import kaleidot725.highestpeaks.databinding.StyleFragmentBinding
import kaleidot725.highestpeaks.di.repository.FormatRepository
import kaleidot725.highestpeaks.di.repository.StyleRepository
import kaleidot725.highestpeaks.di.service.FormatEditor
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.di.service.StyleEditor
import kaleidot725.highestpeaks.ui.edit.format.FormatRecyclerAdapter
import kaleidot725.highestpeaks.ui.edit.format.FormatViewModel
import kaleidot725.highestpeaks.ui.edit.format.FormatViewModelFactory
import javax.inject.Inject

class StyleFragment : Fragment() {

    companion object {
        fun newInstance() = StyleFragment()
    }

    private lateinit var viewModel: StyleViewModel

    @Inject
    lateinit var pictureEditor: PictureEditor

    @Inject
    lateinit var styleEditor : StyleEditor

    @Inject
    lateinit var repository: StyleRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.style_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, StyleViewModelFactory(pictureEditor, styleEditor, repository)).get(
            StyleViewModel::class.java)

        val binding = DataBindingUtil.bind<StyleFragmentBinding>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel

        val recyclerView = this.view?.findViewById<RecyclerView>(R.id.style_recycler_view)
        recyclerView?.adapter = StyleRecyclerAdapter(this, viewModel.styleRecyclerViewModel.value ?: listOf())
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.setHasFixedSize(true)
    }
}
