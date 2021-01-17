package com.target.targetcasestudy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.Status
import com.target.targetcasestudy.data.ApiData
import com.target.targetcasestudy.viewmodel.PostViewModel


class DealListFragment : Fragment() {
  private lateinit var viewModel: PostViewModel
  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: DealItemAdapter
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view =  inflater.inflate(R.layout.fragment_deal_list, container, false)

    view.findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(requireContext())
    view.findViewById<RecyclerView>(R.id.recycler_view).adapter = context?.let { DealItemAdapter(it) }

    recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
    adapter = context?.let { DealItemAdapter(it) }!!
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(context)
    viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
    setupWebCallObservers()
    val context = activity as AppCompatActivity
    adapter.onItemClick = { products ->
      Log.d("KUNWAR", products.title)
      context.replaceFragment(DealItemFragment(products))
      // do something with your item


    }

    return view
  }
  private fun retrieveList(users: ApiData) {
    adapter.apply {
      addUsers(users)
      notifyDataSetChanged()
    }
  }

  private fun setupWebCallObservers() {
    activity?.let {
      viewModel.getUsers().observe(it, Observer {
        it?.let { resource ->
          when (resource.status) {
            Status.SUCCESS -> {
              recyclerView.visibility = View.VISIBLE
              // progressBar.visibility = View.GONE
              resource.data?.let { users ->
                retrieveList(users)
              }
            }
            Status.ERROR -> {
              recyclerView.visibility = View.VISIBLE
              //progressBar.visibility = View.GONE
              Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()

            }
            Status.LOADING -> {
              //progressBar.visibility = View.VISIBLE
              recyclerView.visibility = View.GONE
            }
          }
        }
    })
    }
  }

}
