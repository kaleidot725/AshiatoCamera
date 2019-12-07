package kaleidot725.ashiato.ui.edit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.ActivityEditBinding
import kaleidot725.ashiato.ui.edit.color.ColorFragment
import kaleidot725.ashiato.ui.edit.confirm.ConfirmFragment
import kaleidot725.ashiato.ui.edit.format.FormatFragment
import kaleidot725.ashiato.ui.edit.position.PositionFragment
import kaleidot725.ashiato.ui.edit.rotation.RotationFragment
import kaleidot725.ashiato.ui.edit.style.StyleFragment
import org.koin.android.viewmodel.ext.android.viewModel

class EditActivity : AppCompatActivity(), EditNavigator {
    val editViewModel: EditViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        editViewModel.navigator = this // FIXME
        val binding: ActivityEditBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_edit)
        binding.viewmodel = editViewModel
        binding.lifecycleOwner = this

        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, FormatFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction()
            .replace(R.id.display_content, ConfirmFragment.newInstance()).commit()
    }

    override fun navigateFormatEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, FormatFragment.newInstance()).commit()
        return true
    }

    override fun navigateStyleEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, StyleFragment.newInstance()).commit()
        return true
    }

    override fun navigateColorEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, ColorFragment.newInstance()).commit()
        return true
    }

    override fun navigatePositionEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, PositionFragment.newInstance())
            .commit()
        return true
    }

    override fun navigateRotationEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, RotationFragment.newInstance())
            .commit()
        return true
    }

    override fun exit(): Boolean {
        finish()
        return true
    }
}
