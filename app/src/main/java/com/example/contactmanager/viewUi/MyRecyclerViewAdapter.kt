package com.example.contactmanager.viewUi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanager.R
import com.example.contactmanager.databinding.CardviewBinding
import com.example.contactmanager.room.User

class MyRecyclerViewAdapter(private val usersList:List<User>,
                 private val clickListner:(User)->Unit
                            ):RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : CardviewBinding = DataBindingUtil.
        inflate(layoutInflater, R.layout.cardview, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position],clickListner)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

}

class MyViewHolder(val binding:CardviewBinding):RecyclerView.ViewHolder(binding.root){
   fun bind(user: User,clickListner: (User) -> Unit){
       binding.nameTextView.text=user.name
       binding.emailTextView.text=user.email
       binding.listItemLayout.setOnClickListener{
           clickListner(user)
       }

   }
}