package de.syntaxinstitut.e_sport_news.ui.Youtube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.adapter.YoutubeAdapter
import de.syntaxinstitut.e_sport_news.databinding.FragmentYoutubeBinding
import de.syntaxinstitut.e_sport_news.ui.main.YoutubeViewModel

class YoutubeFragment : Fragment() {
    private val viewModel: YoutubeViewModel by viewModels()

    private lateinit var binding: FragmentYoutubeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoutubeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       binding.rvYoutube.setHasFixedSize(true)


        val helper: SnapHelper = PagerSnapHelper()
        helper.attachToRecyclerView(binding.rvYoutube)

        viewModel.video.observe(viewLifecycleOwner){
            //binding.rvYoutube.adapter = YoutubeAdapter(it)

        }
    }
}