package com.example.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyRepositoryImpl(counter:Int) : MyRepository { //repository 인터페이스 상속

    //db데이터를 가지고 왔다고 가정
    private val liveCounter = MutableLiveData<Int>(counter)

    override fun getCounter(): LiveData<Int> {
       return liveCounter
    }

    override fun increaseCounter(){
       liveCounter.value = liveCounter.value?.plus(1)
    }
}