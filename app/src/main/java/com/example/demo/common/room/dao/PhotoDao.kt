package com.example.demo.common.room.dao

import androidx.room.*
import com.example.demo.common.model.Photo
import io.reactivex.Completable
import io.reactivex.Maybe


@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(user: Photo): Completable

    @Delete
    fun remove(user: Photo): Completable

    @Query("SELECT * FROM Tbl_photo")
    fun getAll(): Maybe<List<Photo>>

    @Insert
    fun insertAll(photos: List<Photo>) : Completable

    @Delete
    fun delete(user: Photo)

    @Query("DELETE FROM Tbl_photo")
    fun deleteAll() : Completable
}