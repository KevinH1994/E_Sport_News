package de.syntaxinstitut.e_sport_news.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.adapter.NewsAdapter
import de.syntaxinstitut.e_sport_news.databinding.FragmentHomeBinding


/**
 * Fragment 2
 */
class HomeScreenFragment : Fragment(R.layout.list_item_home) {

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentHomeBinding

    /** Das ViewModel zu diesem Fragment */
    private val viewModel: HomeScreenViewModel by viewModels()

    /* -------------------- Lifecycle -------------------- */
    /**
     * Lifecycle Methode wenn das View erstellt wird
     *
     * @param inflater                Layout Inflater
     * @param container               View Gruppe
     * @param savedInstanceState      Eventuelle saveStates
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadNews()
        viewModel.news.observe(viewLifecycleOwner, Observer {
            val adapter = NewsAdapter(it.news, requireContext())
            binding.rvNews.adapter = adapter
            println(it)
        })
        binding.rvNews.setHasFixedSize(true)
    }
}
