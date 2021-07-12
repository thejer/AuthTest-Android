package com.appstyx.authtest.utils

import android.widget.TextView
import com.appstyx.authtest.common.KeyValueDropDownView

fun clearTextLayoutError(vararg textLayouts: TextView) {
    for (textLayout in textLayouts) textLayout.error = null
}

fun validateDropdownViews(vararg autoCompleteTextViews: KeyValueDropDownView): Boolean {
    clearTextLayoutError(*autoCompleteTextViews)
    for (autoCompleteTextView in autoCompleteTextViews) {
        if (!autoCompleteTextView.isSelectionValid()) {
            autoCompleteTextView.error = "This field is empty!"
            return false
        }
    }
    return true
}

fun validateTextLayouts(vararg textLayouts: TextView): Boolean {
    clearTextLayoutError(*textLayouts)
    for (textLayout in textLayouts) {
        if (!textLayout.validate()) return false
    }
    return true
}
