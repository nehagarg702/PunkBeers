package com.example.punkbeers.di.module

import android.content.Context
import com.example.punkbeers.di.qualifiers.ActivityContext
import com.example.punkbeers.di.qualifiers.ApplicationContext
import com.example.punkbeers.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(var context: Context) {

    @Provides
    @ApplicationContext
    @ApplicationScope
    fun getApplicationcontext():Context
    {
        return context.applicationContext
    }
}