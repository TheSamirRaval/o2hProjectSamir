package com.example.demo.utils

import android.app.Activity
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.util.DisplayMetrics
import android.util.Log
import com.example.demo.base.extentions.convertDate
import java.text.SimpleDateFormat
import java.util.*
import android.net.NetworkInfo

import android.net.ConnectivityManager




class Utils {
    companion object {

        const val RC_SIGN_IN = 0x101

//        private var googleApiClient: GoogleApiClient? = null
        val inputFormatExpiryDate = "yyyy-MM-dd"
        val outPutFormatExpiryDate = "MMMM d,yyyy"
        val inputFormatOrder = "yyyy-MM-dd HH:mm:ss"
        val outputFormatOrder = "E dd MMM yyyy,hh:mm a"


        private fun hasGPSDevice(context: Activity): Boolean {
            val mgr = context
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val providers = mgr.allProviders ?: return false
            return providers.contains(LocationManager.GPS_PROVIDER)
        }

        fun getScreenWidth(activity: Activity): Int {
            val metrics: DisplayMetrics = activity.resources.displayMetrics
            return metrics.widthPixels

        }

        fun getScreenHeight(activity: Activity): Int {
            val metrics: DisplayMetrics = activity.resources.displayMetrics
            return metrics.heightPixels
        }

        fun getCompleteAddressString(
            context: Activity,
            LATITUDE: Double,
            LONGITUDE: Double
        ): String {
            var strAdd = ""
            val geocoder = Geocoder(context, Locale.getDefault())
            try {
                val addresses: List<Address> =
                    geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
                if (addresses != null) {
                    val returnedAddress: Address = addresses[0]
                    val strReturnedAddress = StringBuilder("")
                    for (i in 0..returnedAddress.maxAddressLineIndex) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
                    }
                    strAdd = strReturnedAddress.toString()
                    Log.e("MyCurrentloctionaddress", strReturnedAddress.toString())
                } else {
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return strAdd
        }

        fun getUniqueStringEveryTime(): String? {
            val dNow = Date()
            val ft = SimpleDateFormat("yyyyMMddHHmmssSS")
            val datetime: String = ft.format(dNow)
            try {
                Thread.sleep(1)
            } catch (e: java.lang.Exception) {
            }
            return datetime
        }


        public fun getDayNumberSuffix(date1: String): String {

            val inputDateFormat = "yyyy-MM-dd HH:mm:ss"
            var format: String? = null
            if (date1.endsWith("1") && !date1.endsWith("11"))
                format =
                    "d'st' MMM yyyy hh:mm a"
            else if (date1.endsWith("2") && !date1.endsWith(
                    "12"
                )
            )
                format =
                    "d'nd' MMM yyyy hh:mm a"
            else if (date1.endsWith("3") && !date1.endsWith(
                    "13"
                )
            )
                format = "d'rd'MMM yyyy hh:mm a"
            else
                format =
                    "d'th' MMM yyyy hh:mm a"

            Log.e("date", "--" + date1)
            val yourDate: String = date1.convertDate(inputDateFormat, format)
            return yourDate
        }

        fun isNetworkAvailable(ctx: Context): Boolean {
            val connectivityManager = ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED
                    || connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!
                .state == NetworkInfo.State.CONNECTED)
        }
    }
}