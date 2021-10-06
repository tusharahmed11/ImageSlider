package com.example.imageslider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide

class SliderAdapter(private var imageList: List<String>):
    RecyclerView.Adapter<SliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_list_item,parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val sliderImageView = itemView.findViewById<ImageView>(R.id.appCompatImageView)

        fun bind(data: String){

            sliderImageView.load(data)

   /*         Glide.with(itemView.context)
                .load(data)
                .into(sliderImageView)*/
        }
    }
}