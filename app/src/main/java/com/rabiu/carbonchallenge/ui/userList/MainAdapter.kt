package com.rabiu.carbonchallenge.ui.userList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rabiu.carbonchallenge.data.entities.User
import com.rabiu.carbonchallenge.databinding.ListItemUserBinding


class MainAdapter(private val listener: OnItemClickListener) : ListAdapter<User, MainAdapter.ViewHolder>(UserDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
       holder.bind(createOnClickListener(item),item)
    }


    private fun createOnClickListener(item: User): View.OnClickListener {
        return View.OnClickListener {
              listener.onItemClicked(item.id)
        }
    }


    class ViewHolder private constructor(val binding: ListItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener,item: User) {
            binding.item = item
            binding.clickListener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemUserBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    interface OnItemClickListener {
        fun onItemClicked(userId: String)
    }
}





class UserDiffCallback : DiffUtil.ItemCallback<User>(){

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }


}