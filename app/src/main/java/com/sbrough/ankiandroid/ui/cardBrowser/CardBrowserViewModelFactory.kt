package com.sbrough.ankiandroid.ui.cardBrowser

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sbrough.ankiandroid.AnkiApp

/**
 * View model factory for the CardBrowserViewModel
 *
 * Takes in a Deck id for the ViewModel to use
 */
class CardBrowserViewModelFactory(private val deckId: Long, val appContext: Application = AnkiApp.appContext) : ViewModelProvider.AndroidViewModelFactory(AnkiApp.appContext) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CardBrowserViewModel(appContext, deckId) as T
    }
}