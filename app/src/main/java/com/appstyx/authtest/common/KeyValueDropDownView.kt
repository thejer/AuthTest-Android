package com.appstyx.authtest.common

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.appstyx.authtest.data.model.KeyValue

class KeyValueDropDownView<T>(
    context: Context,
    attrs: AttributeSet?
) : AppCompatAutoCompleteTextView(context, attrs) {

    private var selectedPosition = -1

    var onItemClick: ((`object`: T) -> Unit)? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setOnItemClickListener { _, _, position, _ ->
            selectedPosition = position
            if (isSelectionValid()) {
                onItemClick?.invoke(getItemObject())
                error = null
            }
        }
    }

    var selectedItem: KeyValue<T>
        get() = adapter.getItem(selectedPosition) as KeyValue<T>
        set(value) {
            val position = (adapter as ArrayAdapter<KeyValue<T>>).getPosition(value)
            if (position >= 0) {
                selectedPosition = position
                this.setText(value.toString(), false)
            }
        }

    fun getItemObject() = selectedItem.`object`

    fun getSelectedItemKey() = selectedItem.key

    fun getSelectedItemId() = selectedItem.id

    fun isSelectionValid() = selectedPosition >= 0
}