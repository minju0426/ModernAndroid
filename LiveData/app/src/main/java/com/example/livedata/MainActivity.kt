package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /* 뷰바인딩
    //바인딩 초기화
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(binding.root)
        //데이터바인딩
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        // 팩토리 패턴 : 객체를 만드는 부분을 sub class에서 함
        val factory = MyViewModelFactory(20, this)
        val myViewModel by viewModels<MyViewModel>() { factory }


        //바인딩라이프사이클오너
        binding.lifecycleOwner = this
        binding.viewmodel = myViewModel



        binding.button.setOnClickListener {
            //livecounter +1
            if(binding.viewmodel?.hasChecked?.value == true) //nullable
                myViewModel.liveCounter.value = myViewModel.liveCounter.value?.plus(1)
        }


        /*
        //라이브데이터 옵저빙 -> UI표시로직을 버튼클릭리스너에 두지않아도됨
        myViewModel.modifierCounter.observe(this) { counter ->
            binding.textView.text = counter.toString()
        }
        */
        //LiveData 옵저빙 대신 xml에서 데이터바인딩을 시켜줌


    }
}