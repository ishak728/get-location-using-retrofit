package com.ishak.locationproject.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishak.locationproject.Adapter.LocationAdapter
import com.ishak.locationproject.Model.Location
import com.ishak.locationproject.R
import com.ishak.locationproject.ViewModel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private lateinit var viewmodel: FeedViewModel
    private  var locationAdapter= LocationAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        location_list.layoutManager=LinearLayoutManager(context)
        location_list.adapter=locationAdapter
        viewmodel=ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewmodel.GetDataFromApi()
        observeLiveData()

        refreshLayout.setOnRefreshListener {
            println("calisti")
            viewmodel.locationLoading.value=true
            location_list.visibility=View.INVISIBLE
            viewmodel.GetDataFromApi()
            observeLiveData()
        }


    }

    fun observeLiveData(){
        viewmodel.locationList.observe(viewLifecycleOwner, Observer {locationList->
        locationList?.let {
            locationAdapter.updateLocation(it)
            location_list.visibility=View.VISIBLE
            println("size"+it.size)
        }

        })
        viewmodel.locationLoading.observe(viewLifecycleOwner, Observer { locationLoading->
            locationLoading?.let {
                if(it){
                    location_progress.visibility=View.VISIBLE

                }
                else{
                    location_progress.visibility=View.INVISIBLE
                }

            }


        })


    }
}