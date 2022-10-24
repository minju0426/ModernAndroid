package com.example.livedata

import android.view.animation.Transformation
import androidx.lifecycle.*


class MyViewModel(
    _counter: Int,
    private val repositoryImpl: MyRepositoryImpl, //view모델에서 repositoryImpl 생성자를 받음
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    //repository Pattern
    val counterFromRepository :LiveData<Int> = repositoryImpl.getCounter()

    fun increaseCounter(){
        repositoryImpl.increaseCounter()
    }

    //livedata로 카운터 생성
    var liveCounter: MutableLiveData<Int> = MutableLiveData(_counter)

    //map 으로 라이브데이터 변경
    val modifierCounter: LiveData<String> = Transformations.map(liveCounter) { counter ->
        "$counter 입니다"
    }
    //repository에서 가져온 값 변경
    val modifiedCounter = Transformations.map(counterFromRepository){
        counter -> "$counter 입니다"
    }

    var hasChecked: MutableLiveData<Boolean> = MutableLiveData(false)


    var counter = savedStateHandle.get<Int>(SAVE_STATE_KEY) ?: _counter //null이면 초기값으로 설정

    fun saveState() { //key와 counter값을 저장하는 함수
        savedStateHandle.set(SAVE_STATE_KEY, counter)
    }

    companion object { //저장과 읽기에 사용될 key
        private const val SAVE_STATE_KEY = "counter"
    }


}