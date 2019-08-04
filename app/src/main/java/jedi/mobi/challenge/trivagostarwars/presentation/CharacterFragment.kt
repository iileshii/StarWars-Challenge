package jedi.mobi.challenge.trivagostarwars.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jedi.mobi.challenge.trivagostarwars.R
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : DialogFragment() {

    companion object {
        private const val KEY_CHARACTER_ID = "characterId"

        val TAG: String = CharacterFragment::class.java.simpleName

        fun newInstance(characterId: Long) =
            CharacterFragment().apply {
                arguments = Bundle().apply { putLong(KEY_CHARACTER_ID, characterId) }
            }
    }

    private val characterId: Long by lazy {
        arguments?.getLong(KEY_CHARACTER_ID) ?: throw IllegalArgumentException("Can't work without character Id")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this)[CharacterViewModel::class.java]
        viewModel.getCharacter(characterId).observe(viewLifecycleOwner, Observer(::updateCharacter))
    }

    private fun updateCharacter(character: StarWarsCharacter) {
        name.text = character.name
        birth_year.text = character.birthYear
        height_cm.text = character.heightCm.toString()
        height_in.text = character.heightInch.toString()
        height_ft.text = character.heightFeet.toString()
    }
}