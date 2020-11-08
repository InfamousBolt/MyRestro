package com.example.myRestro

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel : ViewModel() {
    private val restaurantsLiveData: MutableLiveData<MutableList<Restaurant>>
    private val isRefreshingLiveData: MutableLiveData<Boolean>


    init {
        Log.i(TAG, "init")
        restaurantsLiveData = MutableLiveData()
        restaurantsLiveData.value = createRestro()
        isRefreshingLiveData = MutableLiveData()
        isRefreshingLiveData.value = false
    }

    fun getRestros(): LiveData<MutableList<Restaurant>> {
        return restaurantsLiveData
    }

    fun getIsRefreshing(): LiveData<Boolean> {
        return isRefreshingLiveData
    }

    fun fetchNewRestro() {
        Log.i(TAG, "fetchNewRestro")
        // indicate that we're in "refreshing" state
        isRefreshingLiveData.value = true
        Handler().postDelayed(Runnable {
            // add the new contact
            val restaurants = restaurantsLiveData.value
            restaurants?.add(0,
                Restaurant(
                    "Krusty Krab",
                    "Address: Baker's street",
                    "Speciality: Patty",
                    "5.0 *"
                )
            )
            restaurantsLiveData.value = restaurants
            isRefreshingLiveData.value = false
        }, 1_000)
    }

    private fun createRestro(): MutableList<Restaurant> {
        Log.i(TAG, "createRestaurants")
        val restaurants = mutableListOf<Restaurant>()
        for (i in 1..150) restaurants.add(
            Restaurant(
                "Special Restro#$i",
                "Address: Unique Address#$i",
                "Speciality: Chicken Dinner",
                "4.5 *"
            )
        )
        return restaurants
    }
}
