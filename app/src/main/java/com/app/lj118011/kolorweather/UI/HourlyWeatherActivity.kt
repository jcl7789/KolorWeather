package com.app.lj118011.kolorweather.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.app.lj118011.kolorweather.Modelos.Hora
import com.app.lj118011.kolorweather.R
import kotlinx.android.synthetic.main.activity_hourly_weather.*

class HourlyWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly_weather)
        intent.let {
            val hours:ArrayList<Hora> = it.getParcelableArrayListExtra(MainActivity.CLIMAPORHORA)
            Toast.makeText(this,hours[0].getHoraFormateada(), Toast.LENGTH_LONG).show()
        }
        lst_climaHorario.emptyView = tv_sinDatos
    }
}
