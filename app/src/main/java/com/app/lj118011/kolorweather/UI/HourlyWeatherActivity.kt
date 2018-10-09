package com.app.lj118011.kolorweather.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.app.lj118011.kolorweather.Adapters.HoraAdapter
import com.app.lj118011.kolorweather.Modelos.Hora
import com.app.lj118011.kolorweather.R
import kotlinx.android.synthetic.main.activity_hourly_weather.*

class HourlyWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly_weather)
        lst_climaHorario.layoutManager = LinearLayoutManager(this)
        intent.let {
            val hours:ArrayList<Hora> = it.getParcelableArrayListExtra(MainActivity.CLIMAPORHORA)
            lst_climaHorario.adapter = HoraAdapter(hours)
        }
        //lst_climaHorario. = tv_sinDatos
    }
}
