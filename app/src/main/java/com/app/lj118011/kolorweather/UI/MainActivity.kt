package com.app.lj118011.kolorweather.UI

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.app.lj118011.kolorweather.API.*
import com.app.lj118011.kolorweather.Modelos.ClimaActual
import com.app.lj118011.kolorweather.Modelos.Dia
import com.app.lj118011.kolorweather.Modelos.Hora
import com.app.lj118011.kolorweather.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    var days:ArrayList<Dia> = ArrayList()
    var hours:ArrayList<Hora> = ArrayList()
    var location:Location = Location(LocationManager.NETWORK_PROVIDER)

    companion object {
        val CLIMADIARIO = "CLIMA_DIARIO"
        val CLIMAPORHORA = "CLIMA_POR_HORA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        while(!rutina_permisos())
            Toast.makeText(this,"Es necesario que aceptes los permisos para continuar", Toast.LENGTH_LONG)
        getUbicacion()
        getClima()

    }

    private fun getUbicacion() {

    }

    private fun rutina_permisos(): Boolean {
        if(
                Build.VERSION.SDK_INT >= 23
                && checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        )
        {
            var permisos:Array<String> = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            requestPermissions(permisos, 100)
            var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            return true
        } else return false
    }

    private fun getClima() {
        val latitud = location.latitude
        val longitud = location.longitude
        val locale = getString(R.string.locale)
        val units = getString(R.string.units)
        val url = "$DARK_SKY_URL/$API_KEY/$latitud,$longitud?lang=$locale&units=$units"
        val queue = Volley.newRequestQueue(this)
        queue.add(obtenerRequest(url))
    }

    fun obtenerRequest(url:String):StringRequest {
        return StringRequest(Request.Method.GET, url,
                Response.Listener<String> {
                    val climaActual = getClimaActualFromJson(it)
                    days = getClimaDiarioFromJson(it)
                    hours = getClimaHorarioFromJson(it)
                    construirUI_ClimaActual(climaActual)
                },
                Response.ErrorListener {
                    manejarError()
                })

    }

    private fun manejarError() {

        Log.d("$TAG", "Error al recuperar el clima desde DarkSky")
        Snackbar.make(main, getString(R.string.error), Snackbar.LENGTH_INDEFINITE).setAction(
                getString(R.string.reintentar)
        ) {  getClima() }.show()
    }

    private fun construirUI_ClimaActual(climaActual: ClimaActual) {
        tv_temp.text = getString(R.string.tempPlaceholder, climaActual.temp.toInt())
        tv_clima.text = climaActual.summary
        var probabilidad = climaActual.precip * 100
        tv_prob.text = getString(R.string.probPlaceholder, probabilidad.toInt())
        iv_temp.setImageDrawable(ResourcesCompat.getDrawable(resources, climaActual.getIconResource(), null))
        tv_ciudad.text = climaActual.timezone
    }

    fun iniciarClimaDiario(view:View){
        val intent = Intent(this, DailyWeatherActivity::class.java).apply{
            putParcelableArrayListExtra(CLIMADIARIO, days)
        }
        startActivity(intent)
    }

    fun iniciarClimaPorHorario(view:View){
        val intent = Intent(this, HourlyWeatherActivity::class.java).apply{
            putParcelableArrayListExtra(CLIMAPORHORA, hours)
        }
        startActivity(intent)
    }
}
