package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.database.NewsDatabase
import com.example.newsappinkotlin.model.NewsModel
import com.example.newsappinkotlin.ui.adapter.Adapter
import kotlinx.android.synthetic.main.fragment_saved_items.*

class SavedItemsFragment : Fragment() {
    lateinit var dbNews: NewsDatabase
    lateinit var Adapter: Adapter
    lateinit var llm: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_saved_items, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fun onClickCard() {
            findNavController().navigate(
                R.id.action_savedItemsFragment_to_itemDetailsFragment)
        }

        dbNews = NewsDatabase.getSavedItems(requireActivity().applicationContext)
        Adapter = Adapter(
            dbNews.getNewsDao().getAllSavedNews() as MutableList<NewsModel>,{ onClickCard()},requireActivity().applicationContext )
        llm = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        rv_saved.adapter = Adapter
        rv_saved.layoutManager = llm
    }
}