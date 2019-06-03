package kaleidot725.highestpeaks.preview

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.PreviewFragmentBinding
import kaleidot725.highestpeaks.model.data.Holder
import kaleidot725.highestpeaks.model.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository
import javax.inject.Inject

class PreviewFragment : Fragment() {

    companion object {
        fun newInstance() = PreviewFragment()
    }

    private lateinit var viewModel: PreviewViewModel

    @Inject
    lateinit var repository : PictureRepository

    @Inject
    lateinit var preview : Holder<Picture>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.preview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, PreviewViewModelFactory(repository, preview)).get(PreviewViewModel::class.java)

        val binding = DataBindingUtil.bind<PreviewFragmentBinding>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel
    }
}
