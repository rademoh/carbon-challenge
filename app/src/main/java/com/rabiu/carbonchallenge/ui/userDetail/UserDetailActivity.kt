package com.rabiu.carbonchallenge.ui.userDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.rabiu.carbonchallenge.api.Result
import com.rabiu.carbonchallenge.databinding.ActivityUserDetailBinding
import com.rabiu.carbonchallenge.util.hide
import com.rabiu.carbonchallenge.util.show
import org.koin.android.viewmodel.ext.android.viewModel

class UserDetailActivity : AppCompatActivity() {

    private val userDetailViewModel : UserDetailViewModel by viewModel()
    private lateinit var binding : ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        binding.viewModel = userDetailViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        intent?.getStringExtra(EXTRAS_USER_ID)?.let {
            getUserDetails(it)
        }
    }

    private fun getUserDetails(it: String) {
        userDetailViewModel.getUserDetail(it).observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let {
                        userDetailViewModel.bindUI(it)

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


    companion object {

        const val EXTRAS_USER_ID = "user_id"

    }
}