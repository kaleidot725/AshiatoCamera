package kaleidot725.highestpeaks.ui.edit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.ActivityEditBinding
import kaleidot725.highestpeaks.ui.edit.color.ColorFragment
import kaleidot725.highestpeaks.ui.edit.style.StyleFragment
import kaleidot725.highestpeaks.ui.edit.confirm.ConfirmFragment
import kaleidot725.highestpeaks.ui.edit.format.FormatFragment
import kaleidot725.highestpeaks.ui.edit.position.PositionFragment
import kaleidot725.highestpeaks.ui.edit.rotation.RotationFragment
import javax.inject.Inject

class EditActivity : AppCompatActivity(), HasSupportFragmentInjector, EditNavigator {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var viewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        viewModel = ViewModelProviders.of(this, EditViewModelFactory(this)).get(EditViewModel::class.java)
        val binding : ActivityEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit)
        binding.viewmodel =  viewModel
        binding.lifecycleOwner = this

        supportFragmentManager.beginTransaction().replace(R.id.display_content, ConfirmFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.editmenu_content, FormatFragment.newInstance()).commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun navigateFormatEditor(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.editmenu_content, FormatFragment.newInstance()).commit()
        return true
    }

    override fun navigateStyleEditor(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.editmenu_content, StyleFragment.newInstance()).commit()
        return true
    }

    override fun navigateColorEditor() : Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.editmenu_content, ColorFragment.newInstance()).commit()
        return true
    }

    override fun navigatePositionEditor(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.editmenu_content, PositionFragment.newInstance()).commit()
        return true
    }

    override fun navigateRotationEditor(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.editmenu_content, RotationFragment.newInstance()).commit()
        return true
    }

    override fun exit(): Boolean {
        finish()
        return true
    }
}
