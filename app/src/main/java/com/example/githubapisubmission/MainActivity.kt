package com.example.githubapisubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapisubmission.adapter.UserListAdapter
import com.example.githubapisubmission.data.database.Favorite
import com.example.githubapisubmission.data.database.SettingPreferences
import com.example.githubapisubmission.data.database.dataStore
import com.example.githubapisubmission.data.repository.FavoriteRepository
import com.example.githubapisubmission.data.response.UsersResponse
import com.example.githubapisubmission.data.viewmodel.MainActivityViewModel
import com.example.githubapisubmission.data.viewmodel.PreferencesViewModel
import com.example.githubapisubmission.data.viewmodel.PreferencesViewModelFactory
import com.example.githubapisubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainActivityViewModel by viewModels()
    private var nightMode: Boolean = false
    private lateinit var preferencesViewModel: PreferencesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //testGround()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupPreferences()
        supportActionBar?.title = "Users Search"
        recyclerView = binding.recyclerViewMain
        setupSearchBar()
        setContentView(binding.root)
        observerUsers()

    }

    private fun observerUsers() {
        val responseObserver = Observer<UsersResponse?> {

            if (it != null) {
                setupRecyclerView(it)
                showLoading(false)
            }
        }
        viewModel.readResponseBoydMain.observe(this, responseObserver)
    }

    private fun setupPreferences() {
        val pref = SettingPreferences.getInstance(application.dataStore)
        preferencesViewModel =
            ViewModelProvider(
                this,
                PreferencesViewModelFactory(pref)
            )[PreferencesViewModel::class.java]
        preferencesViewModel.getThemeSettings().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                nightMode = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                nightMode = false
            }
        }
    }

//    private fun testGround() {
//        //TODO,Testing Room Database
//        val db = FavoriteRepository(application)
//        val fav1 = Favorite("hkvil", "https://avatars.githubusercontent.com/u/75840967?v=4")
//        val fav2 = Favorite("dd","https://tes.com")
//        //db.delete(fav1)
//        db.insert(fav1)
//        db.insert(fav2)
//        //val fav2 = Favorite("linus", "https://linus.com")
//        //Log.d("API",db.equals(db.insert(fav1)).toString())
//
//
//        //db.insert(fav2)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (nightMode) {
            menuInflater.inflate(R.menu.light, menu)
        } else {
            menuInflater.inflate(R.menu.night, menu)
        }
        menuInflater.inflate(R.menu.favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.toogle_menu -> {
                nightMode = !nightMode
                preferencesViewModel.saveThemeSetting(nightMode)
                true
            }

            R.id.favorite_menu -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }


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
                viewModel.getUsersAPI(searchView.text.toString(), this@MainActivity)
                showLoading(true)
                false
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}