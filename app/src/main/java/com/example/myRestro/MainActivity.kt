package com.example.myRestro

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrecyclerview.R
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")

        val restaurants = mutableListOf<Restaurant>()
        val restaurantAdapter =
            RestaurantAdapter(this, restaurants)
        rvContacts.adapter = restaurantAdapter
        rvContacts.layoutManager = LinearLayoutManager(this)

        val model = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        model.getRestros().observe(this, Observer { contactsSnapshot ->
            Log.i(TAG, "Received contacts from view model")
            restaurants.clear()
            restaurants.addAll(contactsSnapshot)
            restaurantAdapter.notifyDataSetChanged()
        })
        model.getIsRefreshing().observe(this, Observer { isRefreshing ->
            Log.i(TAG, "Received isRefreshing from view model")
            swipeContainer.isRefreshing = isRefreshing
        })

        swipeContainer.setOnRefreshListener {
            // Show the refreshing UI and fetch new data
            model.fetchNewRestro()
        }
    }
}
