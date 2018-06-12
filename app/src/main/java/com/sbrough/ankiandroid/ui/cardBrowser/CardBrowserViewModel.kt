package com.sbrough.ankiandroid.ui.cardBrowser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sbrough.ankiandroid.db.AnkiDatabase
import com.sbrough.ankiandroid.db.entities.Card

class CardBrowserViewModel(appContext: Application, private val deckId: Long) : AndroidViewModel(appContext) {

    var cards = MutableLiveData<List<Card>>()

    fun cardsLiveData(): LiveData<List<Card>> = cards

    // Gets the list of cards from the repository, setting up the live data
    fun getCards() {
        cards.value = AnkiDatabase.get().cardDao().getDeckOfCards(deckId)
    }

}
