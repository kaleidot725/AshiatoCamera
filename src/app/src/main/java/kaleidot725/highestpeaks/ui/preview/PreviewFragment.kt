package kaleidot725.highestpeaks.ui.preview

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.PreviewFragmentBinding
import kaleidot725.highestpeaks.di.repository.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository
import javax.inject.Inject
import javax.inject.Named

class PreviewFragment : Fragment() {

    companion object {
        fun newInstance(position : Int) = PreviewFragment().also {
            val bundle = Bundle()
            bundle.putInt("position", position)
            it.arguments = bundle
        }
    }

    private lateinit var viewModel: PreviewViewModel

    @Inject
    lateinit var repository : PictureRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.preview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt("position")
        viewModel = ViewModelProviders.of(this, PreviewViewModelFactory(repository, repository.all()[position ?: 0])).get(PreviewViewModel::class.java)

        val binding = DataBindingUtil.bind<PreviewFragmentBinding>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel
    }
}
