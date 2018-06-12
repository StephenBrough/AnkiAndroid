package com.sbrough.ankiandroid.ui.deckBrowser

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sbrough.ankiandroid.R
import com.sbrough.ankiandroid.db.AnkiDatabase
import com.sbrough.ankiandroid.db.json.Deck
import com.sbrough.ankiandroid.ui.cardBrowser.CardBrowserFragmentArgs
import kotlinx.android.synthetic.main.fragment_deck_browser.*
import timber.log.Timber

class DeckBrowserFragment : Fragment() {

    private lateinit var viewModel: DeckBrowserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_deck_browser, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeckBrowserViewModel::class.java)

        deckRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        deckRecyclerView.adapter = DeckBrowserAdapter() { deckId ->
            NavHostFragment.findNavController(this)
                    .navigate(R.id.actionViewCardDeck,
                            CardBrowserFragmentArgs.Builder().setDeckId(deckId.toString()).build().toBundle())
        }

        viewModel.mutableDeckList.observe(this, Observer<List<Deck>> { deckList ->
            (deckRecyclerView.adapter as DeckBrowserAdapter).submitList(deckList)
        })

        empty.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            val chooser = Intent.createChooser(intent, "Choose your weapon")
            startActivityForResult(chooser, IMPORT_REQUEST_CODE)
        }

        button.setOnClickListener { Timber.d("${AnkiDatabase.get().cardDao().getAllCards()}") }

        viewModel.init()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.d("Result: $requestCode, $resultCode: $data")
        if (resultCode == Activity.RESULT_OK) {
            // Succeeded in getting a file
            if (requestCode == IMPORT_REQUEST_CODE) {
                data?.data?.let { ankiFileUri ->
                    viewModel.loadDb(ankiFileUri)
                }
            }
        }
    }

    companion object {
        const val IMPORT_REQUEST_CODE = 99

        fun newInstance() = DeckBrowserFragment()
    }

}
