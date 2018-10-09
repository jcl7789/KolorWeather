package com.app.lj118011.kolorweather.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.app.lj118011.kolorweather.Modelos.Hora
import com.app.lj118011.kolorweather.R
import com.app.lj118011.kolorweather.XT.inflate
import kotlinx.android.synthetic.main.hourly_item.view.*

class HoraAdapter(val horas: ArrayList<Hora>): RecyclerView.Adapter<HoraAdapter.HoraViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoraViewHolder
            = HoraViewHolder(parent.inflate(R.layout.hourly_item))

    override fun getItemCount(): Int = horas.size

    override fun onBindViewHolder(holder: HoraViewHolder, position: Int) = holder.bind(horas[position])

    class HoraViewHolder(horaria: View): RecyclerView.ViewHolder(horaria){
        fun bind(hora:Hora) = with(itemView){
            tv_horario_hora.text = hora.getHoraFormateada()
            tv_horario_prob.text = "${hora.temp.toInt()}º C"
            tv_horario_temp.text = "${hora.precip.toInt()} %"
        }
    }
}