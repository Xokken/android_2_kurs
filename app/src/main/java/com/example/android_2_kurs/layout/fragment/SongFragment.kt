package com.example.android_2_kurs.layout.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_2_kurs.R
import com.example.android_2_kurs.entity.Song
import com.example.android_2_kurs.entity.SongRepository
import kotlinx.android.synthetic.main.fragment_song.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSong.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var song: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        imageButtonBack.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, SongListFragment.newInstance("1", "2"))
                ?.commit()
        }
    }

    private fun init(){
        val songList = SongRepository.getRepository()
        imageSongProfile.setImageResource(songList[param1!!].cover)
        textNameProfile.text =songList[param1!!].name
        textAuthorProfile.text = songList[param1!!].author

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentSong.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int) =
            SongFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}