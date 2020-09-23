package com.humam.alquranexsercise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.humam.alquranexsercise.R
import com.humam.alquranexsercise.model.ModelSurah

class SurahAdapter (private val mContext: Context, private val items: List<ModelSurah>, private val onSelected: onSelectedData) : RecyclerView.Adapter<SurahAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_surah, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: SurahAdapter.ViewHolder, position: Int) {
        val data = items[position]
        holder.txtNumber.text = data.nomor
        holder.txtAyat.text = data.nama
        holder.txtInfo.text = data.type + " - " + data.ayat + " Ayat "
        holder.txtName.text = data.asma
        holder.cvSurah.setOnClickListener { onSelected.onSelected(data) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //Class Holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvSurah: CardView
        var txtNumber: TextView
        var txtAyat: TextView
        var txtInfo: TextView
        var txtName: TextView

        init {
            cvSurah = itemView.findViewById(R.id.cvSurah)
            txtNumber = itemView.findViewById(R.id.tvNumber)
            txtAyat = itemView.findViewById(R.id.tvAyat)
            txtInfo = itemView.findViewById(R.id.tvInfo)
            txtName = itemView.findViewById(R.id.tvName)
        }
    }

    open interface onSelectedData {
        fun onSelected(modelSurah: ModelSurah)
    }
}