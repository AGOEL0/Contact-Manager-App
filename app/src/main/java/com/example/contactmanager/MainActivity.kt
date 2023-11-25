package com.example.contactmanager

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanager.myviewModel.UserViewModel
import com.example.contactmanager.myviewModel.UserViewModelFactory
import com.example.contactmanager.databinding.ActivityMainBinding
import com.example.contactmanager.room.User
import com.example.contactmanager.room.UserDatabase
import com.example.contactmanager.room.UserRepository
import com.example.contactmanager.viewUi.MyRecyclerViewAdapter

class MainActivity : ComponentActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Room DataBase
        val dao=UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        userViewModel=ViewModelProvider(this,factory).get(UserViewModel::class.java)
        binding.userViewModel=userViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recylerView.layoutManager=LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        userViewModel.users.observe(this, Observer {
            binding.recylerView.adapter = MyRecyclerViewAdapter(
                it, {selectedItem: User -> listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(this,
            "Selected name is ${selectedItem.name}",
            Toast.LENGTH_LONG).show()

        userViewModel.initUpdateAndDelete(selectedItem)
    }
}