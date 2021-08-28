package com.example.demo.base

import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.R
import com.example.demo.common.api.ApiInterface
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    private var alertDialog: AlertDialog? = null

    lateinit var mcontext: Activity
    val compositeDisposable = CompositeDisposable()
    private lateinit var apiClient: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        if (isAndroidTV()) {
            requestWindowFeature(Window.FEATURE_OPTIONS_PANEL)
        }
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration)
        mcontext = this
        initializeLoader()
    }

    private fun initializeLoader() {
    }

    /**
     * This method is used to stop scaling the font size from device setting font size
     */
    private fun adjustFontScale(configuration: Configuration) {
        configuration.fontScale = 1.0.toFloat()
        val metrics = resources.displayMetrics
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun isAndroidTV(): Boolean {
        val uiModeManager = getSystemService(Activity.UI_MODE_SERVICE) as UiModeManager
        return uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION
    }

    protected fun Disposable.autoDispose() {
        compositeDisposable.add(this)
    }

    /**
     * To show Toast message in app
     */
    fun showToast(msg: String?) {
        showAlert(message = msg,onPositiveButtonClickListener = null)
    }

    /**
     * To show Toast message in app
     */
    fun showToast(msg: String?,onPositiveButtonClickListener: DialogInterface.OnClickListener?) {
        showAlert(message = msg,onPositiveButtonClickListener = onPositiveButtonClickListener)
    }

    /**
     * This method is used to show default alert for app by passing message.
     */
    private fun showAlert(
        title: String? = null,
        message: String?,
        buttonText: String? = getString(R.string.ok), onPositiveButtonClickListener: DialogInterface.OnClickListener?
    ) {
        this.runOnUiThread {
            if (title == null) {
                val alertDialogTemp = AlertDialog.Builder(this)
                alertDialogTemp.setMessage(message)
                alertDialogTemp.setPositiveButton(buttonText, onPositiveButtonClickListener)

                alertDialog = alertDialogTemp.create()
                alertDialog?.show()

            } else {

                val alertDialogTemp = AlertDialog.Builder(this)
                alertDialogTemp.setTitle(title)
                alertDialogTemp.setMessage(message)
                alertDialogTemp.setPositiveButton(buttonText, onPositiveButtonClickListener)

                alertDialog = alertDialogTemp.create()
                alertDialog?.show()
            }
        }
    }

}