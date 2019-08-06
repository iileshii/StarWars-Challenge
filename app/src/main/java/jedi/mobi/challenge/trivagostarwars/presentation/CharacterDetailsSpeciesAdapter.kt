package jedi.mobi.challenge.trivagostarwars.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jedi.mobi.challenge.trivagostarwars.R
import jedi.mobi.challenge.trivagostarwars.domain.Species
import kotlinx.android.synthetic.main.list_item_species.view.*

internal class CharacterDetailsSpeciesAdapter : RecyclerView.Adapter<CharacterDetailsSpeciesAdapter.ViewHolder>() {

    private val list = mutableListOf<Species>()

    fun updateData(newData: List<Species>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_species, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(list[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun update(species: Species) {
            itemView.name.text = species.name
            itemView.language.text = species.language
            itemView.homeworld.text = species.homeWorld
        }
    }
}

