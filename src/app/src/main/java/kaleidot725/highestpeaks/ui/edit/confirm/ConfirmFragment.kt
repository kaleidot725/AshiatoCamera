package kaleidot725.highestpeaks.ui.edit.confirm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kaleidot725.highestpeaks.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.databinding.ConfirmFragmentBinding
import kaleidot725.highestpeaks.di.holder.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.highestpeaks.di.service.FormatEditor
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.ui.edit.EditNavigator
import kaleidot725.michetimer.model.repository.PictureRepository
import javax.inject.Inject
import javax.inject.Named

class ConfirmFragment : Fragment() {

    companion object {
        fun newInstance() = ConfirmFragment()
    }

    @Inject
    lateinit var navigator: EditNavigator

    @Inject
    lateinit var formatEditor : FormatEditor

    @Inject
    lateinit var pictureEditor : PictureEditor

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var pictureRepository: PictureRepository

    private lateinit var viewModel : ConfirmViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.confirm_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProviders.of(this, ConfirmViewModelFactory(navigator, pictureRepository, formatEditor, pictureEditor)).get(ConfirmViewModel::class.java)
        val binding = DataBindingUtil.bind<ConfirmFragmentBinding>(view)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel
    }
}
