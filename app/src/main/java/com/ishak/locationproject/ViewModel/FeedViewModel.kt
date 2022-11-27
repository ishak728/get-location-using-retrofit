package com.ishak.locationproject.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ishak.locationproject.Model.Location
import com.ishak.locationproject.Service.LocationApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel: ViewModel() {

    val locationList= MutableLiveData<List<Location>>()
    val locatioError= MutableLiveData<Boolean>()
    val locationLoading= MutableLiveData<Boolean>()
    private val disposable= CompositeDisposable()
    private val locationApiService=LocationApiService()

    fun GetDataFromApi(){
        disposable.add(
            locationApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Location>>(){
                    override fun onSuccess(t: List<Location>) {
                        locationList.value=t
                        locationLoading.value=false
                        locatioError.value=false
                    }

                    override fun onError(e: Throwable) {
                       locationLoading.value=false
                        locatioError.value=true
                        e.printStackTrace()
                    }

                })
        )
    }


}