package com.byKirilov.saturdaydiscussionclub

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class JokesAdapter(private val jokes: MutableList<String>)
    : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    class JokesViewHolder(val cardView: MaterialCardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_joke, parent, false) as MaterialCardView
        return JokesViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val jokeText = holder.cardView.findViewById<TextView>(R.id.joke_text)
        jokeText.text = jokes[jokes.size - position - 1]
    }

    override fun getItemCount(): Int {
        return jokes.size
    }
}