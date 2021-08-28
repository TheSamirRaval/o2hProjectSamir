package com.example.demo.common.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class ImagesResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val prev_page: String,
    val total_results: Int
)


@Entity(tableName = "Tbl_photo")
data class Photo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rawId") var rawId: Int = 0,
    @ColumnInfo(name = "avg_color")
    var avg_color: String,
    @ColumnInfo(name = "height")
    var height: Int,
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "liked")
    var liked: Boolean,
    @ColumnInfo(name = "photographer")
    var photographer: String,
    @ColumnInfo(name = "photographer_id")
    var photographer_id: Int,
    @ColumnInfo(name = "photographer_url")
    var photographer_url: String,
    @ColumnInfo(name = "src")
    @Ignore
    var src: Src?,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "width")
    var width: Int,
    @ColumnInfo(name = "portrait")
    var portrait: String
) {
    constructor() : this(0, "", 0, 0, false, "", 0, "", null, "", 0, "")
}

data class Src(
    val landscape: String,
    val large: String,
    val large2x: String,
    val medium: String,
    val original: String,
    val portrait: String,
    val small: String,
    val tiny: String
)