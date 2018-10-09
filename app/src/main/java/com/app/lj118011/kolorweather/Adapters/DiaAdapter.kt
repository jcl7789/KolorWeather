package com.app.lj118011.kolorweather.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.app.lj118011.kolorweather.Modelos.Dia
import com.app.lj118011.kolorweather.R
import com.app.lj118011.kolorweather.XT.inflate

class DiaAdapter(val context: Context, val dataSource: ArrayList<Dia>) : BaseAdapter() {
    override fun getView(position: Int, currentView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view: View
        if (currentView == null) {
            view = parent.inflate(R.layout.daily_item)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = currentView.tag as ViewHolder
            view = currentView
        }
        val currentDay = dataSource[position]
        viewHolder.apply {
            tv_dia.text = currentDay.getTiempoFormateado()
            tv_minTemp.text = "Min: ${currentDay.minTemp.toInt()} ºC"
            tv_maxTemp.text = "Max: ${currentDay.maxTemp.toInt()} ºC"
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return 0 // Sin uso
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private class ViewHolder(view: View) {
        val tv_dia = view.findViewById<TextView>(R.id.tv_dia)
        val tv_minTemp = view.findViewById<TextView>(R.id.tv_minTemp)
        val tv_maxTemp = view.findViewById<TextView>(R.id.tv_maxTemp)
    }
}