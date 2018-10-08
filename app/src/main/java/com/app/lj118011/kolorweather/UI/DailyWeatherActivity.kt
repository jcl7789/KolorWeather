package com.app.lj118011.kolorweather.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.lj118011.kolorweather.Adapters.DayAdapter
import com.app.lj118011.kolorweather.Modelos.Dia
import com.app.lj118011.kolorweather.R
import kotlinx.android.synthetic.main.activity_daily_weather.*

class DailyWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_weather)
        intent.let {
            val days = it.getParcelableArrayListExtra<Dia>(MainActivity.CLIMADIARIO)
            val adapter = DayAdapter(this,days)
            lst_climaDiario.adapter = adapter
        }
        lst_climaDiario.emptyView = tv_sinDatos
    }
}
