package com.example.punkbeers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.punkbeers.repository.BeerRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(var beerRepository: BeerRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeerViewModel(beerRepository) as T
    }
}