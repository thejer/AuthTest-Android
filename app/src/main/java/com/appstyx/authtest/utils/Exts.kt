package com.appstyx.authtest.utils

import android.app.Activity
import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

fun Activity.hideKeyBoard() {
    val view = currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun TextView.isEmpty() = text.isNullOrEmpty()

fun TextView.validate(errorMessage: String = "This Field is empty!") =
    (!isEmpty()).also { if (!it) error = errorMessage }

fun TextView.isValidEmail(errorMessage: String = "Email is invalid") =
    (!isEmpty() && Patterns.EMAIL_ADDRESS.matcher(this.text).matches()).also { if (!it) error = errorMessage }

var EditText.stringContent: String
    get() = text.toString()
    set(value) = setText(value)

fun View.showSnackbar(
    snackbarText: String,
    timeLength: Int,
    actionString: String?,
    action: () -> Unit?
): Snackbar {
    val snackbar = Snackbar.make(this, snackbarText, timeLength)
    snackbar.run {
        setAction(actionString) {
            snackbar.dismiss()
            action()
        }
        show()
    }
    return snackbar
}
