package com.example.livedata

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["app:progressScaled","android:max"], requireAll = true)
fun setProgress(progressBar: ProgressBar, counter : Int, max:Int){

    //progress max가 100임

    progressBar.progress = (counter*2).coerceAtMost(max) //2배씩 증가 100넘지않도록 (프로그래스는 50까지)

}