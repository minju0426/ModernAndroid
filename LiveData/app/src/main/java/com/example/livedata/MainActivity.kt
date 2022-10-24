package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //바인딩 초기화
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val factory = MyViewModelFactory(100,this)
        val myViewModel by viewModels<MyViewModel>() { factory }

        binding.button.setOnClickListener {
            //livecounter +1
            myViewModel.liveCounter.value = myViewModel.liveCounter.value?.plus(1)
        }
        //옵저빙 -> UI표시로직을 버튼클릭리스너에 두지않아도됨
        myViewModel.modifierCounter.observe(this){ counter ->
            binding.textView.text = counter.toString()
        }
    }
}