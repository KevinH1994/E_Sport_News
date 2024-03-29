package de.syntaxinstitut.e_sport_news.ui.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.adapter.SearchAdapter
import de.syntaxinstitut.e_sport_news.databinding.FragmentSearchBinding

class SearchFragment: Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var search = view.findViewById<TextInputEditText>(R.id.game_name)

        viewModel.loadData("BatMan")

        binding.rvResults.setHasFixedSize(true)

        viewModel.result.observe(
            viewLifecycleOwner
        ){
            binding.rvResults.adapter = SearchAdapter(it)
        }
            search.addTextChangedListener{

            if (!it!!.equals("")) {
                viewModel.loadData(it.toString().replace(" ", "%20"))
            }
        }
        }

}





