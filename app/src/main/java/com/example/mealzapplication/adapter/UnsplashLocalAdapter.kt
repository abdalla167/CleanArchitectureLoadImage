package com.example.mealzapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.domain.entity.DataModelAnimalLocal
import com.example.domain.entity.UnsplashPhoto
import com.example.mealzapplication.R
import com.example.mealzapplication.databinding.ItemUnsplashPhotoBinding

class UnsplashLocalAdapter (private val listner: OnItemClickListner,  var mList: List<DataModelAnimalLocal>) : RecyclerView.Adapter<UnsplashLocalAdapter.PhotoViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem =mList.get(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner  class PhotoViewHolder(private val binding: ItemUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {

            binding.root.setOnClickListener{
                val position =bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val item =mList.get(position)
                    if(item != null)
                    {
                        listner.OnItemClick(item)
                    }
                }

            }

        }

        fun bind(photo: DataModelAnimalLocal) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)

                textViewUserName.text = photo.user.username
            }
        }
    }

    interface OnItemClickListner {
        fun OnItemClick(photo: DataModelAnimalLocal)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
                oldItem == newItem
        }
    }
}