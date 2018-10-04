package com.app.lj118011.kolorweather.API

import com.app.lj118011.kolorweather.Modelos.ClimaActual
import org.json.JSONObject

class JSONParser {

    fun getClimaActualFromJson(response:JSONObject): ClimaActual {
        val timezone = response.getString(TIMEZONE)
        val currentJson = response.getJSONObject(ACTUAL)
        with(currentJson){
            return ClimaActual(
                    getString(ICONO),
                    getString(DETALLE),
                    getDouble(TEMPERATURA),
                    getDouble(PROBABILIDAD_PRECIP), timezone
            )
        }
    }
}