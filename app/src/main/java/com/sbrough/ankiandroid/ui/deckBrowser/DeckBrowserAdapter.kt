package com.sbrough.ankiandroid.ui.deckBrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sbrough.ankiandroid.R
import com.sbrough.ankiandroid.db.json.Deck
import com.sbrough.ankiandroid.ui.deckBrowser.DeckBrowserAdapter.DeckBrowserViewHolder
import kotlinx.android.synthetic.main.item_deck.view.*

class DeckBrowserAdapter(private val clickListener: (Long) -> Unit) : ListAdapter<Deck, DeckBrowserViewHolder>(DeckBrowserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckBrowserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DeckBrowserViewHolder(inflater.inflate(R.layout.item_deck, parent, false))
    }

    override fun onBindViewHolder(holder: DeckBrowserViewHolder, position: Int) = holder.bind(getItem(position), clickListener)

    class DeckBrowserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(deck: Deck, clickListener: (Long) -> Unit) {
                itemView.name.text = deck.name
                itemView.setOnClickListener { clickListener(deck.id) }
            }
    }
}

class DeckBrowserDiffCallback : DiffUtil.ItemCallback<Deck>() {
    override fun areItemsTheSame(oldItem: Deck, newItem: Deck): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Deck, newItem: Deck): Boolean {
        // TODO: Might want to add other comparable fields
        return oldItem.name == newItem.name
    }

}

