package com.patricio.pelisapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewModel<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}