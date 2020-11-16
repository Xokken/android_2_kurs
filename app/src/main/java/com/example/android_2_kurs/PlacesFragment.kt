package com.example.android_2_kurs

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.android_2_kurs.repositories.PlacesRepository
import kotlinx.android.synthetic.main.fragment_places.*
import kotlinx.android.synthetic.main.places_dialog.view.*

class PlacesFragment: Fragment(){

    var adapter : PlaceAdapter? = null
    var dialogBuilder: AlertDialog.Builder? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PlaceAdapter(PlacesRepository.placesList)
        val dialogLayout = LayoutInflater.from(context).inflate(R.layout.places_dialog,null)


        dialogBuilder = AlertDialog.Builder(context)
            .setTitle("Add")
            .setView(dialogLayout)
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int ->
                    val place = Place(
                                    PlacesRepository.placesList.size,
                                    dialogLayout.places_dialog_edit_title.text.toString(),
                                    dialogLayout.places_dialog_edit_description.text.toString())
                    PlacesRepository.addPlace(dialogLayout.places_dialog_edit_position.text.toString(),place)

                    updateData()

            }

        val dialog = dialogBuilder?.create()
        fragment_places_list.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        fragment_places_list.adapter = adapter
        fragment_places_add_button.setOnClickListener{
            dialog?.show()
        }


    }

    private fun updateData(){
        adapter?.updateData(PlacesRepository.placesList)
        adapter?.notifyDataSetChanged()
    }
}