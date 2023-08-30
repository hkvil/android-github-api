package com.example.githubapisubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapisubmission.adapter.UserListAdapter
import com.example.githubapisubmission.data.response.UsersResponse
import com.example.githubapisubmission.data.viewmodel.MainActivityViewModel
import com.example.githubapisubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.title = "Github Users Search"
        recyclerView = binding.recyclerViewMain
        setupSearchBar()
        setContentView(binding.root)

        val responseObserver = Observer<UsersResponse?> {

            if (it != null) {
                setupRecyclerView(it)
                showLoading(false)
            }
        }
        viewModel.readResponseBoydMain.observe(this, responseObserver)

    }


    private fun setupRecyclerView(list: UsersResponse) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserListAdapter(list)

    }

    private fun setupSearchBar() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.text = searchView.text
                searchView.hide()
                viewModel.getUsersAPI(searchView.text.toString(),this@MainActivity)
                showLoading(true)
                false
            }
        }
    }

    private fun showLoading(isLoading:Boolean){
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}