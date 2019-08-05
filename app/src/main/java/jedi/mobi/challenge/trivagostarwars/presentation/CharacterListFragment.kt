package jedi.mobi.challenge.trivagostarwars.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
import android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
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

    private lateinit var viewModel: CharacterListViewModel
    private val adapter = CharacterListAdapter(::onItemClick)
    private val mediator = MediatorLiveData<List<StarWarsCharacterListItem>>()
    private val observer = Observer(::updateList)

    private fun onItemClick(item: StarWarsCharacterListItem) {
        (childFragmentManager.findFragmentById(R.id.container) as? CharacterFragment)?.updateCharacter(item.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[CharacterListViewModel::class.java]
        mediator.observe(viewLifecycleOwner, observer)
        mediator.addSource(viewModel.getCharacterList(), observer)

        initRecycler(view.context)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
        inflater?.inflate(R.menu.menu_search, menu)

        val item = menu?.findItem(R.id.action_search) ?: return
        val safeThemedContext = (activity as? AppCompatActivity)?.supportActionBar?.themedContext ?: return

        initSearchView(item, safeThemedContext)
    }

    private fun initSearchView(item: MenuItem, ctx: Context) {
        val searchView = SearchView(ctx)

        item.setShowAsAction(SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or SHOW_AS_ACTION_IF_ROOM)
        item.actionView = searchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                onSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun onSearch(query: String) {
        mediator.addSource(viewModel.getCharacterList(query), observer)
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