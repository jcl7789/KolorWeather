package com.app.lj118011.kolorweather.API

import com.app.lj118011.kolorweather.Modelos.ClimaActual
import com.app.lj118011.kolorweather.Modelos.Dia
import com.app.lj118011.kolorweather.Modelos.Hora
import com.app.lj118011.kolorweather.XT.iterator
import org.json.JSONObject

    fun getClimaActualFromJson(response: String): ClimaActual {
        val responseJO = JSONObject(response)
        val timezone = responseJO.getString(TIMEZONE)
        val currentJson = responseJO.getJSONObject(ACTUAL)
        with(currentJson){
            return ClimaActual(
                    getString(ICONO),
                    getString(DETALLE),
                    getDouble(TEMPERATURA),
                    getDouble(PROBABILIDAD_PRECIP), timezone
            )
        }

    }

    fun getClimaDiarioFromJson(response: String):ArrayList<Dia>{
        val dailyJson = JSONObject(response).getJSONObject(DIARIO)
        val dayJsonArray = dailyJson.getJSONArray(DATA)
        val dias = ArrayList<Dia>()
        for (dia in dayJsonArray){
            val min = dia.getDouble(TEMP_MINIMA)
            val tiempo = dia.getLong(TIEMPO)
            val max = dia.getDouble(TEMP_MAXIMA)
            val timeZone = dia.getString(TIMEZONE)
            dias.add(Dia(tiempo, min, max, timeZone))
        }
        return dias
    }

fun getClimaHorarioFromJson(response: String):ArrayList<Hora>{
    val hourlyJson = JSONObject(response).getJSONObject(POR_HORA)
    val hoursJsonArray = hourlyJson.getJSONArray(DATA)
    val horas = ArrayList<Hora>()
    for (hora in hoursJsonArray){
        val temp = hora.getDouble(TEMPERATURA)
        val tiempo = hora.getLong(TIEMPO)
        val precip = hora.getDouble(PROBABILIDAD_PRECIP)
        val timeZone = hora.getString(TIMEZONE)
        horas.add(Hora(tiempo, temp, precip, timeZone))
    }
    return horas
}
