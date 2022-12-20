package com.example.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppController:Application()
{

  companion object {
      lateinit var appController:AppController
      fun getInstance(): AppController {
          return appController
      }
  }

    override fun onCreate() {
        super.onCreate()
        appController=this
    }



}