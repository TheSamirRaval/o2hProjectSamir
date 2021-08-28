package com.example.demo.ui.main.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.common.model.Photo
import com.example.demo.databinding.ItemPhotoListBinding
import com.example.demo.ui.main.view.adapter.viewholder.PhotoViewHolder
import timber.log.Timber


class PhotosAdapter(
    var context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var photosList: MutableList<Photo> = mutableListOf()

    /**
     * This method is used to set ArrayList
     */
    @SuppressLint("NotifyDataSetChanged")
    fun addItemList(page: Int, photoList: MutableList<Photo>?) {
        if (page == 1) {
            if (photoList != null) {
                photosList = photoList
            }
            notifyDataSetChanged()
        } else {
            photoList?.let { photosList.addAll(it) }
//            notifyItemRangeInserted(oldSize, arrayShopStatusList.size)
            notifyDataSetChanged()
        }
        Timber.d("Load MOre Page $page size --- ${photosList.size} ")
    }

    /**
     * This method is used to Create View Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemBinding =
            ItemPhotoListBinding.inflate(layoutInflater, parent, false)
        return PhotoViewHolder(
            context,
            itemBinding
        )

    }

    /**
     * This method is used to Binding Adapter
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder) {
            try {
                holder.bind(photosList[position])
            } catch (e: Exception) {

            }
        }
    }

    /**
     * This method is used to count of List
     */
    override fun getItemCount(): Int {
        return photosList.size
    }

}