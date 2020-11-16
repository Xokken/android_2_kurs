package com.example.android_2_kurs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.photos_card.view.*

class CardHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.photos_card_title)
    val descriptionView: TextView = itemView.findViewById(R.id.photos_card_description)
    var photosAdapter:PhotosAdapter? = null

    fun bind(photosCard: PhotosCard){
        titleView.text = photosCard.title
        descriptionView.text = photosCard.description
        photosAdapter = PhotosAdapter(photosCard.imagesList)
        itemView.photos_card_photos_container.adapter = photosAdapter
    }

    companion object{
        fun create(parent: ViewGroup) = CardHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.photos_card,parent,false)
        )
    }
}