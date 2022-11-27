package com.ishak.locationproject.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ishak.locationproject.Model.Location
import com.ishak.locationproject.R
import com.ishak.locationproject.View.FeedFragmentDirections
import kotlinx.android.synthetic.main.recycler_row.view.*

class LocationAdapter(val locationList:ArrayList<Location>):RecyclerView.Adapter<LocationAdapter.LocationHolder>() {

    var array= arrayListOf<Int>(Color.BLUE,Color.CYAN,Color.DKGRAY,Color.MAGENTA,Color.GREEN,Color.RED,Color.WHITE,Color.YELLOW)

    class LocationHolder(var view:View):RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return LocationHolder(view)

    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {



        holder.view.linearLayoutRecyclerView.setBackgroundColor(array.get((position%8)))
        holder.view.countryName.text=locationList.get(position).Country
        holder.view.longitude.text=locationList.get(position).longitude
        holder.view.latitude.text=locationList.get(position).latitude
        holder.itemView.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToMapsFragment(locationList.get(position).longitude!!.toFloat(),locationList.get(position).latitude!!.toFloat())
            Navigation.findNavController(holder.view).navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return locationList.size
    }


    fun updateLocation(newlocationList:List<Location>){
        locationList.clear()
        locationList.addAll(newlocationList)
        notifyDataSetChanged()


    }
}