package com.example.imageslider

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.imageslider.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager2
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: SliderAdapter
    private val imageList = listOf<String>(
        "https://images.unsplash.com/photo-1586227740560-8cf2732c1531?ixid=MnwxMjA3fDF8MHxlZGl0b3JpYWwtZmVlZHw2fHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
        "https://images.unsplash.com/photo-1633378362958-f4a598fa04b4?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyMHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
        "https://images.unsplash.com/photo-1633507105239-b16794c02b6a?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyN3x8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
    )

    private var dots: Array<TextView?> = arrayOfNulls<TextView>(imageList.size)

    private var onImageChangeCallBack = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            addDotView(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mViewPager = binding.slider

        viewPagerAdapter = SliderAdapter(imageList)

        mViewPager.apply {
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(onImageChangeCallBack)
        }

        lifecycleScope.launch {
            while (true){
                for (i in 0..imageList.size){
                    delay(2000)
                    if (i == 0){
                        mViewPager.setCurrentItem(i,false)
                    }else{
                        mViewPager.setCurrentItem(i,true)
                    }
                }
            }
        }
    }

    private fun  addDotView(currentPage: Int){
        binding.linearLayout.removeAllViews()

        for (i in imageList.indices){
            dots[i] = TextView(this)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                dots[i]?.text = Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY)
            }else{
                dots[i]?.text = Html.fromHtml("&#8226")
            }
            dots[i]?.textSize = 38f
            dots[i]?.setTextColor(ContextCompat.getColor(this,R.color.white))
            binding.linearLayout.addView(dots[i])

            if (dots.isNotEmpty()){
                dots[currentPage]?.setTextColor(ContextCompat.getColor(this,R.color.teal_700))
            }
        }
    }

    override fun onDestroy() {
        mViewPager.unregisterOnPageChangeCallback(onImageChangeCallBack)
        super.onDestroy()
    }
}