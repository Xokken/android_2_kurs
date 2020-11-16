package com.example.android_2_kurs.repositories

import com.example.android_2_kurs.Place

object PlacesRepository{

    var placesList:ArrayList<Place> = arrayListOf(
        Place(1,"Hello1","dfds ksdjhf shdjfks djfhsj fdhjskf"),
                                                  Place(2,"Hello2","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(3,"Hello3","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(4,"Hello4","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(5,"Hello5","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss")
    )

    fun addPlace(index:String?,place: Place){
        if(index!=null){
            if (index==""){
                placesList.add(place)
            }
            else{
                if (index.toInt() <= placesList.size){
                    placesList.add(index.toInt(),place)
                }
                else{
                    placesList.add(place)
                }
            }
        }
        else{
            placesList.add(place)
        }

    }
}
