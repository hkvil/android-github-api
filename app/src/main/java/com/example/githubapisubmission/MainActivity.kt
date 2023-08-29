package com.example.githubapisubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapisubmission.adapter.UserListAdapter
import com.example.githubapisubmission.data.viewmodel.SharedViewModel
import com.example.githubapisubmission.data.response.UsersResponse
import com.example.githubapisubmission.data.viewmodel.MainActivityViewModel
import com.example.githubapisubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.title = "Github Users Search"
        recyclerView = binding.recyclerViewMain
        setupSearchBar()
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        val responseObserver = Observer<UsersResponse?> {
            if (it != null) {
                setupRecyclerView(it)
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
                viewModel.getUsersAPI(searchView.text.toString())
                Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}