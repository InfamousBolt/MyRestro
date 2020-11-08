package com.example.myRestro

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrecyclerview.R
import kotlinx.android.synthetic.main.item_restro.view.*

class RestaurantAdapter(private val context: Context, private val restaurants: List<Restaurant>)
    : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    private val TAG = "RestaurantAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restro, parent, false))
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at position $position")
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: Restaurant) {
            itemView.tvName.text = restaurant.name
            itemView.tvAddress.text = restaurant.address
            itemView.tvSpeciality.text = restaurant.speciality
            itemView.tvRatings.text = restaurant.rating

        }
    }
}