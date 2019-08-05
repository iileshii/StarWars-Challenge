package jedi.mobi.challenge.trivagostarwars.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jedi.mobi.challenge.trivagostarwars.R
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : Fragment() {

    companion object {
        val TAG: String = CharacterListFragment::class.java.simpleName

        fun newInstance() = CharacterListFragment()
    }

    private val adapter = CharacterListAdapter(::onItemClick)

    private fun onItemClick(item: StarWarsCharacterListItem) {
        (childFragmentManager.findFragmentById(R.id.container) as? CharacterFragment)?.updateCharacter(item.id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this)[CharacterListViewModel::class.java]
        viewModel.getCharacterList().observe(viewLifecycleOwner, Observer(::updateList))

        initRecycler(view.context)
    }

    private fun initRecycler(context: Context) {
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
            .apply {
                val drawable = ContextCompat.getDrawable(context, R.drawable.divider)
                drawable?.let { setDrawable(it) }
            }

        recycler.addItemDecoration(divider)

        recycler.adapter = adapter
    }

    private fun updateList(list: List<StarWarsCharacterListItem>) {
        adapter.updateData(list)
    }
}