package com.sbrough.ankiandroid.ui.cardBrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sbrough.ankiandroid.R
import com.sbrough.ankiandroid.db.entities.Note
import com.sbrough.ankiandroid.db.json.Deck
import com.sbrough.ankiandroid.ui.cardBrowser.CardBrowserAdapter.CardBrowserViewHolder
import kotlinx.android.synthetic.main.item_deck.view.*


class CardBrowserAdapter (private val clickListener: (Long) -> Unit) : ListAdapter<Note, CardBrowserViewHolder>(CardBrowserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBrowserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CardBrowserViewHolder(inflater.inflate(R.layout.item_deck, parent, false))
    }

    override fun onBindViewHolder(holder: CardBrowserViewHolder, position: Int) = holder.bind(getItem(position), clickListener)

    class CardBrowserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Note, clickListener: (Long) -> Unit) {
            itemView.name.text = card.fields
            itemView.setOnClickListener { clickListener(card.id!!) }
        }
    }
}

class CardBrowserDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        // TODO: Might want to add other comparable fields
        return oldItem.fields == newItem.fields
    }

}

