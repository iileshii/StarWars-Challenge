package jedi.mobi.challenge.trivagostarwars.presentation.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jedi.mobi.challenge.trivagostarwars.R
import jedi.mobi.challenge.trivagostarwars.domain.Film
import kotlinx.android.synthetic.main.list_item_film.view.*

internal class CharacterDetailsFilmAdapter : RecyclerView.Adapter<CharacterDetailsFilmAdapter.ViewHolder>() {

    private val list = mutableListOf<Film>()

    fun updateData(newData: List<Film>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_film, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(list[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun update(film: Film) {
            itemView.title.text = film.title
            itemView.date.text = film.releaseDate
            itemView.opening_crawl.text = film.openingCrawl
        }
    }
}