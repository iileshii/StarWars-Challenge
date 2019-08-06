package jedi.mobi.challenge.trivagostarwars.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jedi.mobi.challenge.trivagostarwars.R
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem
import kotlinx.android.synthetic.main.list_item_character.view.*

internal class CharacterListAdapter(private val listener: (StarWarsCharacterListItem) -> Unit) :
    RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private val characterList = mutableListOf<StarWarsCharacterListItem>()

    fun updateData(newData: List<StarWarsCharacterListItem>) {
        characterList.clear()
        characterList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false)
        return CharacterViewHolder(
            listener,
            view
        )
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.update(characterList[position])
    }

    class CharacterViewHolder(listener: (StarWarsCharacterListItem) -> Unit, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private var currentItem: StarWarsCharacterListItem? = null

        init {
            itemView.setOnClickListener { currentItem?.let { listener.invoke(it) } }
        }

        fun update(item: StarWarsCharacterListItem) {
            currentItem = item
            itemView.name.text = item.name
            itemView.birth_year.text = item.birthYear
        }
    }
}