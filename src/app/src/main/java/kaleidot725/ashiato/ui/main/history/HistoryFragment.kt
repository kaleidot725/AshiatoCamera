package kaleidot725.ashiato.ui.main.history

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import kaleidot725.ashiato.data.service.picture.Picture
import kaleidot725.ashiato.databinding.HistoryFragmentBinding
import kaleidot725.ashiato.ui.preview.PreviewActivity
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File

class HistoryFragment : Fragment(), ActionMode.Callback {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private val historyViewModel: HistoryViewModel by viewModel()

    private var actionMode: ActionMode? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<HistoryFragmentBinding>(inflater, R.layout.history_fragment, container, false)
            .apply {
                vm = historyViewModel
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createView(HistoryFragmentMode.Display)
        historyViewModel.event.removeObservers(this)
        historyViewModel.event.observe(this, Observer {
            when (it) {
                HistoryViewModel.NavEvent.ACTION -> {
                    navigateAction()
                }
                HistoryViewModel.NavEvent.PREVIEW -> {
                    navigatePreview()
                }
                HistoryViewModel.NavEvent.SHARE -> {
                    navigateShare(historyViewModel.shareItems.value ?: listOf())
                }
            }
        })
    }

    override fun onPause() {
        actionMode?.finish()
        actionMode = null
        super.onPause()
    }

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        val inflater = mode.menuInflater
        inflater.inflate(R.menu.history_action_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        actionMode = null
        createView(HistoryFragmentMode.Display)
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.delete -> historyViewModel.delete()
            R.id.share -> historyViewModel.share()
        }

        actionMode?.finish()
        return true
    }

    private fun navigateShare(pictures: List<Picture>) {
        val builder = ShareCompat.IntentBuilder.from(activity as Activity).also { builder ->
            pictures.map { picture -> File(picture.path) }.forEach { file ->
                val shareUri = FileProvider.getUriForFile(
                    activity!!.applicationContext,
                    activity!!.applicationContext.packageName + ".provider",
                    file
                )
                builder.addStream(shareUri)
            }
        }

        val intent = builder.intent
        intent.type = "image/jpg"
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun navigateAction(): Boolean {
        actionMode = this.activity?.startActionMode(this, ActionMode.TYPE_PRIMARY)
        createView(HistoryFragmentMode.Action)
        return true
    }

    private fun navigatePreview() {
        val intent = Intent(context, PreviewActivity::class.java)
        startActivity(intent)
    }

    private fun createView(mode: HistoryFragmentMode) {
        val gridLayoutManager = (recyclerView?.layoutManager as GridLayoutManager?)
        val position = gridLayoutManager?.findFirstCompletelyVisibleItemPosition() ?: 0

        recyclerView = this.view?.findViewById(R.id.picture_recycler_view)
        historyViewModel.pictureViewModels.observe(this, Observer {
            recyclerView?.adapter =
                PictureAdapter(this, historyViewModel.pictureViewModels.value ?: listOf())
            recyclerView?.layoutManager = GridLayoutManager(context, 2)
            recyclerView?.setHasFixedSize(true)
            recyclerView?.scrollToPosition(position)
        })

        historyViewModel.load(mode)
    }
}

