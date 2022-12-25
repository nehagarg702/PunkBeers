package com.example.punkbeers.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkbeers.model.Beer
import com.example.punkbeers.repository.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class BeerViewModel @Inject constructor(val beerRepository: BeerRepository) : ViewModel() {

    val beerList  : MutableLiveData<ArrayList<Beer>>
    get() = beerRepository.beersList as MutableLiveData<ArrayList<Beer>>

    init {
        getBeerData()
    }

    fun getBeerData() {
        beerRepository.error.set(false)
        beerRepository.progressShow.set(true)
        viewModelScope.launch(Dispatchers.IO){
            beerRepository.getBeers()
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        beerRepository.filterBeerList(s.toString())
    }
}