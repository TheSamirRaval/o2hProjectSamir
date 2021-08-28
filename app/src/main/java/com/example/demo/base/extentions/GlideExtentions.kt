package com.example.demo.base.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.demo.R


/**
 * This method is used to load shop Category logo
 */
fun ImageView.loadShopCategoryLogo(storeUrl: String?) {

    val imageView = this
    val storeUrl1 = storeUrl.toString().replace("\\", "");
    if (storeUrl1 != null && storeUrl1.isNullOrBlank().not()) {

        Glide.with(imageView.context)
            .load(storeUrl)
            .override(imageView.measuredWidth, imageView.measuredHeight)
            .apply(RequestOptions.centerInsideTransform())
            .placeholder(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    } else {
        Glide.with(imageView.context)
            .load(R.mipmap.ic_launcher)
            .override(imageView.measuredWidth, imageView.measuredHeight)
            .apply(RequestOptions.centerInsideTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
}


