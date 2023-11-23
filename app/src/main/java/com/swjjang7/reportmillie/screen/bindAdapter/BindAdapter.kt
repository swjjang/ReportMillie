package com.swjjang7.reportmillie.screen.bindAdapter

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}

@BindingAdapter("dateText")
fun loadDateText(view: TextView, dateString: String?) {
    val date = stringToDate(dateString)
    view.text = dateToString(date)
}

private fun stringToDate(dateString: String?): Date? {
    if (dateString.isNullOrEmpty()) {
        return null
    }

    return try {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.KOREA)
        format.parse(dateString)
    } catch (e: Exception) {
        null
    }
}

private fun dateToString(date: Date?): String? {
    date ?: return null

    return try {
        val format = SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss (EEE)", Locale.KOREA)
        format.format(date)
    } catch (e: Exception) {
        null
    }
}

@BindingAdapter("submitList")
fun bindSubmitList(view: RecyclerView, list: List<Any>?) {
    (view.adapter as? ListAdapter<Any, *>)?.submitList(list?.toMutableList())
}
