package com.example.livedata

import android.view.animation.Transformation
import androidx.lifecycle.*


class MyViewModel(_counter : Int, private val savedStateHandle: SavedStateHandle) : ViewModel() {//생성자로 savedStateHandle를 받도록

    //livedata로 카운터 생성
    var liveCounter : MutableLiveData<Int> = MutableLiveData(_counter)

    //map 으로 라이브데이터 변경
    val modifierCounter :LiveData<String> = Transformations.map(liveCounter){ counter->
        "$counter 입니다"
    }


    var counter = savedStateHandle.get<Int>(SAVE_STATE_KEY) ?: _counter //null이면 초기값으로 설정

    fun saveState(){ //key와 counter값을 저장하는 함수
        savedStateHandle.set(SAVE_STATE_KEY, counter)
    }

    companion object{ //저장과 읽기에 사용될 key
        private const val SAVE_STATE_KEY = "counter"
    }



}