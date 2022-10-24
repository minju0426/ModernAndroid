package com.example.livedata

import androidx.lifecycle.LiveData

//Repository interface
open interface MyRepository {

    fun getCounter(): LiveData<Int>
    fun increaseCounter()

}