package com.app.lj118011.kolorweather.API

import com.app.lj118011.kolorweather.Modelos.ClimaActual
import com.app.lj118011.kolorweather.Modelos.Dia
import com.app.lj118011.kolorweather.XT.iterator
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

    fun getClimaDiario(response: JSONObject):ArrayList<Dia>{
        val dailyJson = response.getJSONObject(DIARIO)
        val dayJsonArray = dailyJson.getJSONArray(DATA)
        val dias = ArrayList<Dia>()
        for (dia in dayJsonArray){
            val min = dia.getDouble(TEMP_MINIMA)
            val tiempo = dia.getLong(TIEMPO)
            val max = dia.getDouble(TEMP_MAXIMA)
            dias.add(Dia(tiempo, min, max))
        }
        return dias
    }
}