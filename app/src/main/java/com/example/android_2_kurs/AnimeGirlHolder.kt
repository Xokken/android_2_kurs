package com.example.android_2_kurs

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.anime_girl.*
import kotlinx.android.synthetic.main.activity_main.*

class AnimeGirlHolder(
    override val containerView: View,
    private val itemClick: (AnimeGirl) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var animeGirl: AnimeGirl? = null

    init {
        itemView.setOnClickListener {
            val context = itemView.context
            val intent = Intent(context, ProfileGirl::class.java)
            intent.putExtra("id", position.toString())
            context.startActivity(intent)
        }
    }

    fun bind(animeGirl: AnimeGirl) {
        this.animeGirl = animeGirl
        with(animeGirl) {
            nameGirl.text = name
            descGirl.text = desc
            imageGirl.setImageResource(img)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (AnimeGirl) -> Unit): AnimeGirlHolder =
            AnimeGirlHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.anime_girl, parent, false),
                itemClick
            )
    }
}