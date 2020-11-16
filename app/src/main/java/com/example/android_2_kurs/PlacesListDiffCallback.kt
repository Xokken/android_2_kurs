package com.example.android_2_kurs

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class PlacesListDiffCallback(private var oldList: List<Place>, private var newList: List<Place>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val bundle:Bundle = Bundle().apply {
            if(oldList[oldItemPosition].title != (newList[newItemPosition].title)) {
                putString("ARG_TITLE",newList[newItemPosition].title)
            }
            if(oldList[oldItemPosition].description != (newList[newItemPosition].description)) {
                putString("ARG_DESCRIPTION",newList[newItemPosition].description)
            }

        }
        return if (bundle.isEmpty) null else bundle
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == (newList[newItemPosition])
    }

}