package uz.blog.uzpay.screen.utils

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.lang.StringBuilder


fun Context.showMessage(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ImageView.loadImage(url: String){
    Glide.with(this)
        .load(Constants.BASE_URL + url)
        .centerCrop()
        .into(this)
}

fun String.setOffSetNumbers(): String{
    var str = StringBuilder(this)
    if(!str.isEmpty()){
        str.insert(4, " ")
        str.setCharAt(5, '*')
        str.setCharAt(6, '*')
        str.setCharAt(7, '*')
        str.setCharAt(8, '*')
        str.insert(9, " ")
        str.setCharAt(10, '*')
        str.setCharAt(11, '*')
        str.setCharAt(12, '*')
        str.setCharAt(13, '*')
        str.insert(14, " ")
        str.setCharAt(15, '*')
        str.setCharAt(16, '*')
        str.insert(19, " ")

        return str.toString()
    }
    return " "
}