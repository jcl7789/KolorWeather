package com.app.lj118011.kolorweather.XT

import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.json.JSONArray
import org.json.JSONObject

operator fun JSONArray.iterator(): Iterator<JSONObject>
        = (0 until length()).asSequence().map { get(it) as JSONObject }.iterator()

fun ViewGroup.inflate(resource:Int): View = LayoutInflater.from(context).inflate(resource, this, false)

fun View.displaySnack(mensaje:String, lenght_time: Int = Snackbar.LENGTH_INDEFINITE, f: Snackbar.() -> Unit){
    val snackbar = Snackbar.make(this, mensaje, lenght_time)
    snackbar.show()
}

fun Snackbar.action(mensaje: String, listener: (View)-> Unit){
    setAction(mensaje, listener)
}