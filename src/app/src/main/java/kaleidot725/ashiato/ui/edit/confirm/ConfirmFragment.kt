package kaleidot725.ashiato.ui.edit.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.ConfirmFragmentBinding
import kaleidot725.ashiato.di.repository.*
import kaleidot725.ashiato.di.service.picture.*
import kaleidot725.ashiato.ui.edit.EditNavigator
import javax.inject.Inject

class ConfirmFragment : Fragment() {

    companion object {
        fun newInstance() = ConfirmFragment()
    }

    @Inject
    lateinit var navigator: EditNavigator

    @Inject
    lateinit var formatEditor: FormatEditor

    @Inject
    lateinit var colorEditor: ColorEditor

    @Inject
    lateinit var styleEditor: StyleEditor

    @Inject
    lateinit var positionEditor: PositionEditor

    @Inject
    lateinit var pictureEditor: PictureEditor

    @Inject
    lateinit var rotationEditor: RotationEditor

    @Inject
    lateinit var pictureSetting: PermanentPictureSetting

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var formatRepository: FormatRepository

    @Inject
    lateinit var dateTimeRepository: DateTimeRepository

    @Inject
    lateinit var pictureRepository: PictureRepository

    @Inject
    lateinit var angleRepository: AngleRepository

    private lateinit var viewModel: ConfirmViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.confirm_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)

        if (pictureRepository.editPicture == null) {
            navigator.exit()
        }
        
        val factory = ConfirmViewModelFactory(
            navigator,
            pictureEditor,
            formatEditor,
            colorEditor,
            styleEditor,
            positionEditor,
            rotationEditor,
            pictureSetting,
            dateTimeRepository,
            locationRepository,
            formatRepository,
            pictureRepository,
            angleRepository
        )
        viewModel = ViewModelProviders.of(this, factory).get(ConfirmViewModel::class.java)
        viewModel.load()

        val binding = DataBindingUtil.bind<ConfirmFragmentBinding>(view)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel
    }
}
