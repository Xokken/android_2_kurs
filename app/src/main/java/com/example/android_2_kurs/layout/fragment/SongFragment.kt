package com.example.android_2_kurs.layout.fragment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android_2_kurs.R
import com.example.android_2_kurs.entity.Song
import com.example.android_2_kurs.entity.SongRepository
import com.example.android_2_kurs.service.Callback
import com.example.android_2_kurs.service.MusicService
import kotlinx.android.synthetic.main.fragment_song.*
import kotlinx.android.synthetic.main.song_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSong.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongFragment : Fragment() , Callback{
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var musicService: MusicService? = null
    private var song: Song? = null

    private val binderConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            (service as? MusicService.MusicBinder)?.setCallback(this@SongFragment as Callback)
            musicService = (service as? MusicService.MusicBinder)?.getService()
            if(musicService != null){
                initView()
            }

        }

        override fun onServiceDisconnected(className: ComponentName) {
            musicService = null
        }
    }

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
        val songList = SongRepository.getRepository()
        var buf = param1
        if (param1!! < 3) buf = 2
        if (param1!! > 6) buf = 7
        Log.println(Log.DEBUG, "dsa", songList.toString() + "\n" + param1)
        updateView(songList[buf?.minus(2)!!].id)
        imageButtonBack.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
                ?.replace(R.id.fragment_container, SongListFragment.newInstance("1", "2"))
                ?.commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this.context, MusicService::class.java)
        activity?.bindService(intent, binderConnection, Context.BIND_AUTO_CREATE)
    }

    private fun updateView(id:Int){
        (id).let {
            song = SongRepository.getRepository()[it]
            Log.println(Log.DEBUG, "asd", song.toString())
            textNameProfile.text = song?.name
            textAuthorProfile.text = song?.author
            song?.cover?.let { it1 -> imageSongProfile.setImageResource(it1) }
        }
    }

    private fun initView() {
        var id = param1
        id?.let { updateView(it - 1) }
        id?.let { initNavView(it) }
    }

    private fun playOnStop(mediaPlayer: MediaPlayer){
        if (mediaPlayer.isPlaying){
            buttonStop.setImageResource(R.drawable.ic_pause_black_18dp)
        }
        else {
            buttonStop.setImageResource(R.drawable.ic_play_arrow_black_18dp)
        }
    }

    private fun initNavView(id: Int) {
        musicService?.setSong(id - 1)
        musicService?.playSong()

        buttonPrev.setOnClickListener {
            musicService?.playPrevSong()
            updateView(musicService?.currentSong?:0)
        }
        buttonNext.setOnClickListener {
            musicService?.playNextSong()
            updateView(musicService?.currentSong?:0)
        }
        buttonStop.setOnClickListener {
            musicService?.playSong()
            musicService?.mediaPlayer?.let { it1 -> playOnStop(it1) }
            updateView(musicService?.currentSong?:0)
        }

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

    override fun testCallback(id: Int) {
        updateView(id)
    }
}
