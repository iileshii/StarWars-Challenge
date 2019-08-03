package jedi.mobi.challenge.trivagostarwars.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jedi.mobi.challenge.trivagostarwars.R
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem

class CharacterListFragment : Fragment() {

    companion object {
        val TAG = CharacterListFragment::class.java.simpleName

        fun newInstance() = CharacterListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this)[CharacterListViewModel::class.java]
        viewModel.getCharacterList().observe(viewLifecycleOwner, Observer(::updateList))
    }

    private fun updateList(list: List<StarWarsCharacterListItem>) {
        //todo implement list
    }
}