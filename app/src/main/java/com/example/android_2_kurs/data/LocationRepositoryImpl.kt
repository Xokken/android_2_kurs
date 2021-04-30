package com.example.android_2_kurs.data

import android.annotation.SuppressLint
import android.location.Location
import com.example.android_2_kurs.domain.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationRepositoryImpl(
    private val client: FusedLocationProviderClient
): LocationRepository {


    @SuppressLint("MissingPermission")
    override suspend fun getUserLocation() = suspendCancellableCoroutine<Location> { loc ->
        client.lastLocation.addOnSuccessListener {
            loc.resume(it)
        }.addOnFailureListener {
            loc.resumeWithException(it)
        }
    }
}