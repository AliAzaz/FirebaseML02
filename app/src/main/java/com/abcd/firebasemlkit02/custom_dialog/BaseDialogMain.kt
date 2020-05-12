package com.abcd.firebasemlkit02.custom_dialog

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

open class BaseDialogMain(context: Context) : BaseDialogView.UIView, ProgressBar(context) {

    private val ll: LinearLayout = LinearLayout(context)
    private val tvText: TextView = TextView(context)
    private val builder = AlertDialog.Builder(context)
    private lateinit var llParam: LinearLayout.LayoutParams
    private lateinit var baseDialog: AlertDialog

    init {
        setAlertDialog()
    }

    private fun setAlertDialog() {

        ll.orientation = LinearLayout.HORIZONTAL
        ll.gravity = Gravity.CENTER
        llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        ll.layoutParams = llParam
        this.isIndeterminate = true
        tvText.layoutParams = llParam
        setViewToBuilder()
    }


    override fun dismissDialog(builder: AlertDialog) {
        builder.dismiss()
    }

    override fun showDialog(builder: AlertDialog) {
        builder.show()
        builder.window?.let {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(builder.window!!.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            builder.window!!.attributes = layoutParams
        }
    }

    override fun message(message: String) {
        tvText.text = message
    }

    override fun textColor(color: Int) {
        tvText.setTextColor(color)
    }

    override fun textSize(txtsize: Float) {
        tvText.textSize = txtsize
    }

    override fun alertCancellable(cancel: Boolean) {
        builder.setCancelable(cancel)
    }

    override fun getAlertBuilder(): AlertDialog {
        return baseDialog
    }

    override fun padding(left: Int, top: Int, right: Int, bottom: Int) {
        this.setPadding(left, top, right, bottom)
    }

    private fun setViewToBuilder() {
        ll.parent?.let { ll.removeAllViews() }
        ll.addView(this)
        ll.addView(tvText)
        builder.setView(ll)
        baseDialog = builder.create()
    }
}