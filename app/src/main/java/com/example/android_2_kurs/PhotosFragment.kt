package com.example.android_2_kurs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_2_kurs.repositories.CardsRepository
import kotlinx.android.synthetic.main.fragment_photos.*

class PhotosFragment:Fragment() {

    var adapter : CardAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CardAdapter(CardsRepository.cardsList)
        fragment_photos_card_list.adapter = adapter
    }
}
