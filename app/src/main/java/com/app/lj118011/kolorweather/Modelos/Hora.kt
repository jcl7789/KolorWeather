package com.app.lj118011.kolorweather.Modelos

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

class Hora(val time: Long, val temp: Double, val precip: Double, val timeZone : String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hora> {

        override fun createFromParcel(parcel: Parcel): Hora {
            return Hora(parcel)
        }

        override fun newArray(size: Int): Array<Hora?> {
            return arrayOfNulls(size)
        }

    }

    fun getHoraFormateada(): String {
        val formatter = SimpleDateFormat("HH:mm")
        formatter.timeZone = TimeZone.getTimeZone(timeZone)
        val fecha = Date(time * 1000)
        return formatter.format(fecha)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeDouble(temp)
        parcel.writeDouble(precip)
        parcel.writeString(timeZone)
    }
}