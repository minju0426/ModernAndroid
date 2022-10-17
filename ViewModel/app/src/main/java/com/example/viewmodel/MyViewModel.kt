package com.example.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/*
class MyViewModel(_counter : Int) : ViewModel() {
    var counter : Int = _counter
}
*/
class MyViewModel(_counter : Int, private val savedStateHandle: SavedStateHandle) : ViewModel() {//생성자로 savedStateHandle를 받도록



    /*
    그냥 property로 지정시 액티비티 종료하면 counter값이 휘발되므로
    savedState를 이용하여 값 저장
     */

    //savedstate는 시스템이 앱을 강제로 중단했을 경우 데이터를 보존하기 위해 사용하는 임시 저장소
    // finish 종료시에는 동작하지 X

    var counter = savedStateHandle.get<Int>(SAVE_STATE_KEY) ?: _counter //null이면 초기값으로 설정

    fun saveState(){ //key와 counter값을 저장하는 함수
        savedStateHandle.set(SAVE_STATE_KEY, counter)
    }

    companion object{ //저장과 읽기에 사용될 key
        private const val SAVE_STATE_KEY = "counter"
    }



}