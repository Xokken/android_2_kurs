package com.example.android_2_kurs


import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.id_frameLayout, FragmentThree.newInstance("1", "1"))
            .commit()

        imageView1.setOnClickListener {
            resetColors(imageView1)
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
                .replace(R.id.id_frameLayout, FragmentOne.newInstance("1", "1")).commit()

        }

        imageView2.setOnClickListener {
            resetColors(imageView2)
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
                .replace(R.id.id_frameLayout, FragmentTwo.newInstance("1", "1")).commit()

        }

        imageView3.setOnClickListener {
            resetColors(imageView3)
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
                .replace(R.id.id_frameLayout, FragmentThree.newInstance("1", "1")).commit()

        }

        imageView4.setOnClickListener {
            resetColors(imageView4)
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
                .replace(R.id.id_frameLayout, FragmentFour.newInstance("1", "1")).commit()

        }

        imageView5.setOnClickListener {
            resetColors(imageView5)
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
                .replace(R.id.id_frameLayout, FragmentFive.newInstance("1", "1")).commit()

        }

    }

    fun resetColors(a: ImageView){
        imageView1.isSelected = false
        imageView2.isSelected = false
        imageView3.isSelected = false
        imageView4.isSelected = false
        imageView5.isSelected = false
        a.isSelected = true

    }
}
