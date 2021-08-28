package com.example.demo.base

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.demo.MainActivity
import com.example.demo.R
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.json.JSONObject

open class BaseFragment(private val socketConnect: Boolean = true) : Fragment() {

    protected val compositeDisposable = CompositeDisposable()
    lateinit var activity: Activity

    open var jsonObject: JSONObject? = null

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            activity = (context as Activity)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity is MainActivity) {
            mainActivity = activity as MainActivity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    /**
     * To show Toast message in app with 1 second delay.
     */
    protected fun showToast(msg: String?) {
        if (activity is MainActivity) {
            showToast(msg)
        }
    }

    protected fun Disposable.autoDispose() {
        compositeDisposable.add(this)
    }

    /**
     * To show Toast message in app
     */
    fun showToast(
        msg: String?,
        onPositiveButtonClickListener: DialogInterface.OnClickListener?
    ) {
        showAlert(message = msg, onPositiveButtonClickListener = onPositiveButtonClickListener)
    }

    /**
     * This method is used to show default alert for app by passing message.
     */
    fun showAlert(
        title: String? = null,
        message: String?,
        buttonText: String? = getString(R.string.ok),
        onPositiveButtonClickListener: DialogInterface.OnClickListener?
    ) {
        activity.runOnUiThread {
            context?.let { context ->
                if (title == null) {
                    val alertDialogTemp = AlertDialog.Builder(context)
                    alertDialogTemp.setMessage(message)
                    alertDialogTemp.setPositiveButton(buttonText, onPositiveButtonClickListener)
                    alertDialogTemp.create().show()
                } else {
                    val alertDialogTemp = AlertDialog.Builder(context)
                    alertDialogTemp.setTitle(title)
                    alertDialogTemp.setMessage(message)
                    alertDialogTemp.setPositiveButton(buttonText, onPositiveButtonClickListener)
                    alertDialogTemp.create().show()
                }
            }
        }
    }

    /**
     * This method is used to redirect to URL Specified
     */
    fun redirectToURL(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }


    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}