package com.rabiu.carbonchallenge.ui.userList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.rabiu.carbonchallenge.api.Result
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rabiu.carbonchallenge.databinding.ActivityMainBinding
import com.rabiu.carbonchallenge.ui.userDetail.UserDetailActivity
import com.rabiu.carbonchallenge.util.hide
import com.rabiu.carbonchallenge.util.show
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {

    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        setupRecyclerView()
        subscribeUI()

    }

    private fun setupRecyclerView() {
       adapter = MainAdapter(this)
       binding.recyclerView.layoutManager =  LinearLayoutManager(this)
       binding.recyclerView.adapter = adapter
    }

    private fun subscribeUI() {
        mainViewModel.usersList().observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let {
                        adapter.submitList(it)
                    }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG)
                        .show()
                }
            }

        })

    }

    override fun onItemClicked(userId: String) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(UserDetailActivity.EXTRAS_USER_ID, userId)
        startActivity(intent)
    }


}