package com.sbrough.ankiandroid.ui.cardBrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.sbrough.ankiandroid.R
import com.sbrough.ankiandroid.db.AnkiDatabase
import com.sbrough.ankiandroid.db.entities.Card
import com.sbrough.ankiandroid.db.entities.Note
import kotlinx.android.synthetic.main.fragment_card_browser.*

class CardBrowserFragment : Fragment() {

    private lateinit var viewModel: CardBrowserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_card_browser, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deckId = CardBrowserFragmentArgs.fromBundle(arguments).deckId.toLong()

        viewModel = ViewModelProviders.of(this, CardBrowserViewModelFactory(deckId)).get(CardBrowserViewModel::class.java)
        viewModel.cardsLiveData().observe(this, Observer<List<Card>> { deckOfCards ->
            val cards = mutableListOf<Note>()
//            var notes = ""

            deckOfCards?.forEach { card ->
                cards.add(AnkiDatabase.get().noteDao().getNoteWithId(card.notesId))
            }

            (recyclerView.adapter as CardBrowserAdapter).submitList(cards)
//            cards.forEach { note ->
//                notes += note.fields
//            }
//            textView.text = notes
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.adapter = CardBrowserAdapter {}
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        viewModel.getCards()
    }
}
