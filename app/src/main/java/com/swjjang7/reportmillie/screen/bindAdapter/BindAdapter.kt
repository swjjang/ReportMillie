package com.swjjang7.reportmillie.screen.bindAdapter

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("dateText")
fun loadDateText(view: TextView, dateString: String?) {
    // 2023-11-20T03:01:12Z

    val inFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA)
    val date = inFormat.parse(dateString)

    val outFormat = SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss (EEE)", Locale.KOREA)
    view.text = outFormat.format(date)
}
