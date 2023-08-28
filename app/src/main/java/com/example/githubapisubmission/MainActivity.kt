package com.example.githubapisubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapisubmission.adapter.UserListAdapter
import com.example.githubapisubmission.data.ResponseViewModel
import com.example.githubapisubmission.data.response.GithubResponse
import com.example.githubapisubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ResponseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //go() Make auto nav to Detail,FOr testing only
        recyclerView = binding.recyclerViewMain
        setupSearchBar()
        //TODO,SEARCH BAR BELUM BENER
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ResponseViewModel::class.java]
        val responseObserver = Observer<GithubResponse?> {
            if (it != null) {
                setupRecyclerView(it)
            }
        }
        viewModel.readResponseBoydMain.observe(this, responseObserver)

        // viewModel.getUsersAPI("hkvil")

    }


    private fun setupRecyclerView(list: GithubResponse) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserListAdapter(list)

    }

    private fun go() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    private fun setupSearchBar() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionID, event ->
                searchBar.text = searchView.text
                searchView.hide()
                viewModel.getUsersAPI(searchView.text.toString())
                Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}