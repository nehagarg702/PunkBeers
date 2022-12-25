package com.example.punkbeers.di.component

import com.example.punkbeers.di.module.RetrofitModule
import com.example.punkbeers.di.scope.ApplicationScope
import com.example.punkbeers.view.BeerListFragment
import dagger.Component

@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface ApplicationComponent {
    fun inject(activity : BeerListFragment)
}