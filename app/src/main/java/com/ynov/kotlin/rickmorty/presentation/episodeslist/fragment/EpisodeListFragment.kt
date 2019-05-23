package com.ynov.kotlin.rickmorty.presentation.episodeslist.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.base.fragment.BaseFragment
import com.ynov.kotlin.rickmorty.presentation.episodeslist.adapter.EpisodeListAdapter
import com.ynov.kotlin.rickmorty.presentation.episodeslist.viewmodel.EpisodeListViewModel
import com.ynov.kotlin.rickmorty.presentation.extensions.observeSafe
import com.ynov.kotlin.rickmorty.presentation.episodeslist.viewdatawrapper.EpisodeViewDataWrapper
import kotlinx.android.synthetic.main.fragment_episode_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class EpisodeListFragment : BaseFragment() {

    private val episodeListViewModel: EpisodeListViewModel by viewModel()
    private val episodeListAdapter: EpisodeListAdapter by inject()

    override fun layoutId(): Int = R.layout.fragment_episode_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.fragment_episode_list_title_toolbar)
        setUpRecyclerView()
        episodeListViewModel.episodeList.observeSafe(this) {
            updateView(it)
        }
    }

    private fun updateView(listCharacter: List<EpisodeViewDataWrapper>) {
        episodeListAdapter.setItems(listCharacter)
    }

    private fun setUpRecyclerView() {
        rm_fragment_episode_list_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = episodeListAdapter
        }
    }

}