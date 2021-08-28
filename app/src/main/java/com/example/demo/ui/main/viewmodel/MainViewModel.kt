package com.example.demo.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.demo.base.BaseViewModel
import com.example.demo.base.rxjava.autoDispose
import com.example.demo.common.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

/**
 * This is Register Screen view model
 */
class MainViewModel : BaseViewModel() {

    val photos: MutableLiveData<List<Photo>> = MutableLiveData()


    fun callGetPhotosApi(page: Int, perPage: Int) {
        try {
            apiInterface.getStatus(page, perPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ imageResponse ->
                    if (!imageResponse.photos.isNullOrEmpty()) {
                        message.value = imageResponse.next_page

                        imageResponse.photos.forEach { photo ->
                            photo.portrait = photo.src!!.portrait
                        }
                        photos.value = mutableListOf()
                        insertPhotos(page, imageResponse.photos)
                        photos.value = imageResponse.photos

                    } else {
                        errorMessage.value = "error"
                    }
                }, {
                    errorMessage.value = it.toString()
                }).autoDispose(compositeDisposable)

        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    private fun insertPhotos(page: Int, photos: List<Photo>) {
        if (page == 1) {
            photoDao.deleteAll().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(Action {
                    photoDao.insertAll(photos)
                        .observeOn(AndroidSchedulers.mainThread()).doOnComplete {
                        }.subscribeOn(Schedulers.io())
                        .subscribe {
                        }.autoDispose(compositeDisposable)
                }).autoDispose(compositeDisposable)
        } else {
            photoDao.insertAll(photos).observeOn(AndroidSchedulers.mainThread()).doOnComplete {
            }.subscribeOn(Schedulers.io())
                .subscribe {
                }.autoDispose(compositeDisposable)
        }
    }

    fun getLocalPhotos() {
        photoDao.getAll().observeOn(AndroidSchedulers.mainThread()).doOnComplete {
        }.subscribeOn(Schedulers.io())
            .subscribe { it ->
                run {
                    photos.value = it

                }
            }.autoDispose(compositeDisposable)
    }
}