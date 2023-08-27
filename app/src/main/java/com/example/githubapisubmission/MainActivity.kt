package com.example.githubapisubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapisubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        go()
        setupSearchBar()

        setContentView(binding.root)
    }

    private fun go() {
        val intent = Intent(this,DetailActivity::class.java)
        startActivity(intent)
    }

    private fun setupSearchBar() {
        with (binding){
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionID, event ->
                searchBar.text = searchView.text
                searchView.hide()
                false
            }
        }
    }
}