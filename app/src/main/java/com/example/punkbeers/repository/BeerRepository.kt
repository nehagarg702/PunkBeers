package com.example.punkbeers.repository

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.punkbeers.model.Beer
import com.example.punkbeers.network.ApiInterface
import java.text.FieldPosition
import javax.inject.Inject

class BeerRepository @Inject constructor(var apiService : ApiInterface) {

    private val _beersList = MutableLiveData<ArrayList<Beer>>()
    private val _filterBeersList = MutableLiveData<ArrayList<Beer>>()
    val beersList : LiveData<ArrayList<Beer>>
    get() = _beersList
    var error = ObservableBoolean(false)
    var progressShow = ObservableBoolean(true)
    var noResultFoundVisibility = ObservableBoolean(false)

    suspend fun getBeers() {
        val response = apiService.getBeers()
        if(response.isSuccessful && response.body()!=null) {
            progressShow.set(false)
            error.set(false)
            _beersList.postValue(response.body())
            _filterBeersList.postValue(response.body())
        }
        else {
            progressShow.set(false)
            error.set(true)
        }
    }

    fun getBeer(position: Int): Beer {
        return _beersList.value!![position]
    }

    fun filterBeerList(query : String)
    {
        val list = _filterBeersList.value?.filter { it -> it.name!!.contains(query,true) } as ArrayList<Beer>
        _beersList.postValue(list)
        noResultFoundVisibility.set( list.size <= 0)
    }
}