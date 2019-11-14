package com.sxhardha.slocator

import android.app.Application
import com.sxhardha.slocator.di.DaggerMyComponent
import com.sxhardha.slocator.di.MyComponent

class SlocatorApplication : Application() {

    private lateinit var component: MyComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerMyComponent.factory().create(this)
        COMPONENT_INSTANCE = component
    }

    companion object {
        private var COMPONENT_INSTANCE: MyComponent? = null

        @JvmStatic
        fun getAppComponent(): MyComponent = COMPONENT_INSTANCE!!
    }
}