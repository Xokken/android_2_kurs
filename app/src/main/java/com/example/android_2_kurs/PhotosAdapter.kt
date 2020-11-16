package com.example.android_2_kurs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.card_photo.view.*

class PhotosAdapter(private val imagesList:List<Int>): PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return imagesList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.card_photo,container,false)
        view.card_photo_image.setImageResource(imagesList[position])
        Log.println(Log.INFO,"SaberOneLove","Yes! ${imagesList.size}")
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

    }
}