package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.viewmodel.databinding.ActivityMainBinding

/*
    @author : FrozenCoder (cliearl)
 */

class MainActivity : AppCompatActivity() {

    //바인딩 초기화
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
/* 기본
        var counter = 100
        binding.textView.text = counter.toString()
        binding.button.setOnClickListener {
            counter +=1
            binding.textView.text = "$counter"
        }
*/
        /*viewmodel

        //provider로 singleton 뷰모델 생성
        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java) 
        myViewModel.counter = 100
        binding.textView.text =  myViewModel.counter.toString()
        binding.button.setOnClickListener {
            myViewModel.counter +=1
            binding.textView.text = "$myViewModel.counter"
        }
        */

        /*Factory를 가지고 Viewmodel 생성
        //Activity가 재 생성되도 뷰모델의 값을 들고오는것이기때문에 counter 값이 바뀌지 않음

        val factory = MyViewModelFactory(100)
        //1. ViewModelProvider
        //val myViewModel = ViewModelProvider(this,factory).get(MyViewModel::class.java)

        //2. by 쓰면 FragmentViewModelLazy 위임
        val myViewModel by viewModels<MyViewModel>() {factory}

        binding.textView.text = myViewModel.counter.toString()
        binding.button.setOnClickListener {
            myViewModel.counter +=1
            binding.textView.text = myViewModel.counter.toString()
        }

         */


        //3. SavedStateRegistryOwner을 인자에 넘겨주고
        val factory = MyViewModelFactory(100,this)
        val myViewModel by viewModels<MyViewModel>() { factory }

        binding.textView.text = myViewModel.counter.toString()
        binding.button.setOnClickListener {
            myViewModel.counter += 1
            binding.textView.text = myViewModel.counter.toString()
            myViewModel.saveState() //saveStateHandle에 값 저장
        }
    }
}