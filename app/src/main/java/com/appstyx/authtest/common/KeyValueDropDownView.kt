package com.appstyx.authtest.common

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.appstyx.authtest.data.model.KeyValue

class KeyValueDropDownView(
    context: Context,
    attrs: AttributeSet?
) : AppCompatAutoCompleteTextView(context, attrs) {

    private var selectedPosition = -1

    override fun onFinishInflate() {
        super.onFinishInflate()
        setOnItemClickListener { _, _, position, _ ->
            selectedPosition = position
            if (isSelectionValid()) {
                error = null
            }
        }
    }

    var selectedItem: KeyValue
        get() = adapter.getItem(selectedPosition) as KeyValue
        set(value) {
            val position = (adapter as ArrayAdapter<KeyValue>).getPosition(value)
            if (position >= 0) {
                selectedPosition = position
                this.setText(value.toString(), false)
            }
        }

    fun getSelectedItemId() = selectedItem.id

    fun isSelectionValid() = selectedPosition >= 0
}