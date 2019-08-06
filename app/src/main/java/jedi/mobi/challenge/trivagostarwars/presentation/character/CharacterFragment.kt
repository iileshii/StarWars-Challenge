package jedi.mobi.challenge.trivagostarwars.presentation.character

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jedi.mobi.challenge.trivagostarwars.R
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : DialogFragment() {

    companion object {
        private const val KEY_CHARACTER_ID = "characterId"
    }

    private var characterId: Long? = null
    private lateinit var viewModel: CharacterViewModel
    private val observer = Observer<StarWarsCharacter>(::updateCharacter)
    private val filmAdapter = CharacterDetailsFilmAdapter()
    private val speciesAdapter =
        CharacterDetailsSpeciesAdapter()

    fun updateCharacter(id: Long) {
        viewModel.getCharacter(characterId).removeObserver(observer)
        characterId = id
        arguments?.putLong(KEY_CHARACTER_ID, id)
        if (isVisible) {
            observeCharacter()
        }
    }

    private fun observeCharacter() {
        viewModel.getCharacter(characterId).observe(viewLifecycleOwner, observer)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterId = savedInstanceState?.getLong(KEY_CHARACTER_ID) ?: arguments?.getLong(
            KEY_CHARACTER_ID
        )

        initRecyclers(view.context)

        viewModel = ViewModelProviders.of(this)[CharacterViewModel::class.java]

        observeCharacter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        characterId?.let { outState.putLong(KEY_CHARACTER_ID, it) }
    }

    private fun initRecyclers(context: Context) {
        initRecycler(context, films, filmAdapter)
        initRecycler(context, species, speciesAdapter)
    }

    private fun initRecycler(
        context: Context,
        recycler: RecyclerView,
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>
    ) {
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
            .apply {
                val drawable = ContextCompat.getDrawable(context, R.drawable.divider)
                drawable?.let { setDrawable(it) }
            }

        recycler.addItemDecoration(divider)

        recycler.adapter = adapter
    }

    private fun updateCharacter(character: StarWarsCharacter) {
        name.text = character.name
        birth_year.text = character.birthYear
        height_cm.text = character.heightCm.toString()
        height_in.text = character.heightInch.toString()
        height_ft.text = character.heightFeet.toString()
        population.text = character.population.toString()
        speciesAdapter.updateData(character.species)
        filmAdapter.updateData(character.films)
    }
}