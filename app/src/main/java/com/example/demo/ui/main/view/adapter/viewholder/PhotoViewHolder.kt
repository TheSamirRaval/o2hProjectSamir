package com.example.demo.ui.main.view.adapter.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.base.extentions.loadShopCategoryLogo
import com.example.demo.common.model.Photo
import com.example.demo.databinding.ItemPhotoListBinding

class PhotoViewHolder(
    context: Context,
    private val itemBinding: ItemPhotoListBinding
) : RecyclerView.ViewHolder(itemBinding.root) {
    //Display data from List
    fun bind(photo: Photo) {
        itemBinding.ivPhoto.loadShopCategoryLogo(photo.portrait)
    }
}