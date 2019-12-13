package kaleidot725.ashiato.ui.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.ActivityEditBinding
import kaleidot725.ashiato.ui.edit.color.ColorFragment
import kaleidot725.ashiato.ui.edit.confirm.ConfirmFragment
import kaleidot725.ashiato.ui.edit.format.FormatFragment
import kaleidot725.ashiato.ui.edit.position.PositionFragment
import kaleidot725.ashiato.ui.edit.rotation.RotationFragment
import kaleidot725.ashiato.ui.edit.style.StyleFragment
import kaleidot725.ashiato.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class EditActivity : AppCompatActivity() {
    val editViewModel: EditViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.actionbar_edit)

        val binding: ActivityEditBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_edit)
        binding.viewmodel = editViewModel
        binding.lifecycleOwner = this

        editViewModel.event.observe(this, Observer {
            when (it) {
                EditViewModel.NavEvent.Format -> navigateFormatEditor()
                EditViewModel.NavEvent.Style -> navigateStyleEditor()
                EditViewModel.NavEvent.Color -> navigateColorEditor()
                EditViewModel.NavEvent.Position -> navigatePositionEditor()
                EditViewModel.NavEvent.Rotation -> navigateRotationEditor()
                EditViewModel.NavEvent.Exit -> navigateMain()
            }
        })

        val check = findViewById<ImageButton>(R.id.check_button)
        check.setOnClickListener {
            editViewModel.save()
            exit()
        }

        val close = findViewById<ImageButton>(R.id.close_button)
        close.setOnClickListener {
            editViewModel.cancel()
            exit()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, FormatFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction()
            .replace(R.id.display_content, ConfirmFragment.newInstance()).commit()
    }

    override fun onBackPressed() {
        editViewModel.cancel()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onDestroy() {
        super.onDestroy()
        editViewModel.event.removeObservers(this)
    }

    private fun navigateMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateFormatEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, FormatFragment.newInstance()).commit()
        return true
    }

    private fun navigateStyleEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, StyleFragment.newInstance()).commit()
        return true
    }

    private fun navigateColorEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, ColorFragment.newInstance()).commit()
        return true
    }

    private fun navigatePositionEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, PositionFragment.newInstance())
            .commit()
        return true
    }

    private fun navigateRotationEditor(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.editmenu_content, RotationFragment.newInstance())
            .commit()
        return true
    }

    private fun exit(): Boolean {
        finish()
        return true
    }
}
