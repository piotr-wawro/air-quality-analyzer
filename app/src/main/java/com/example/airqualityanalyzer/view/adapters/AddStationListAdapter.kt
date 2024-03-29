package com.example.airqualityanalyzer.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualityanalyzer.R
import com.example.airqualityanalyzer.model.entities.Station
import com.example.airqualityanalyzer.view_model.SensorViewModel
import com.example.airqualityanalyzer.view_model.StationViewModel

class AddStationListAdapter(
    var stations: LiveData<List<Station>>,
    var stationViewModel: StationViewModel,
    var sensorViewModel: SensorViewModel
) :
    RecyclerView.Adapter<AddStationListAdapter.Holder>() {

    inner class Holder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.station_row, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val provinceName = holder.itemView.findViewById<TextView>(R.id.textProvinceName)
        val stationName = holder.itemView.findViewById<TextView>(R.id.textStationName)

        provinceName.text = stations.value?.get(position)?.city?.commune?.provinceName ?: ""
        stationName.text = stations.value?.get(position)?.stationName ?: ""

        holder.itemView.setOnClickListener {
            val station = stations.value?.get(position)!!
            stationViewModel.addStation(station)
            sensorViewModel.addSensorsByStation(station)
        }
    }

    override fun getItemCount(): Int {
        return stations.value?.size ?: 0
    }
}