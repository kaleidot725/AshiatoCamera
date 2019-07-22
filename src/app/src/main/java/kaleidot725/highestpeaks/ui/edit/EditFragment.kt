package kaleidot725.highestpeaks.ui.edit

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kaleidot725.highestpeaks.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kaleidot725.highestpeaks.databinding.EditFragmentBinding
import kaleidot725.highestpeaks.di.repository.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.di.service.PictureEditorImpl
import javax.inject.Inject
import javax.inject.Named


class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }

    @Inject
    lateinit var navigator: EditNavigator

    @Inject
    @field:Named("EditPicture")
    lateinit var editPicture : Holder<Picture>

    @Inject
    lateinit var locationRepository: LocationRepository

    private lateinit var editor : PictureEditor
    private lateinit var viewModel : EditViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AndroidSupportInjection.inject(this)

        editor = PictureEditorImpl(editPicture.lastedValue, Bitmap.Config.ARGB_8888)
        viewModel = ViewModelProviders.of(this, EditViewModelFactory(navigator, locationRepository, editPicture, editor)).get(EditViewModel::class.java)

        val binding = DataBindingUtil.bind<EditFragmentBinding>(view)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel
    }
}
