package com.momon.belajarroom.fragmet.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.momon.belajarroom.R
import com.momon.belajarroom.model.user
import kotlinx.android.synthetic.main.cutom_row.view.*

class listAdapter : RecyclerView.Adapter<listAdapter.MyViewHolder>(){

    private var userList = emptyList<user>()

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cutom_row,parent,false))

    }

    override fun getItemCount(): Int {
        return userList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstname_txt.text = currentItem.firstName
        holder.itemView.lastname_txt.text =currentItem.lastName

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }

    }
    fun  setData(user: List<user>){
        this.userList = user
        notifyDataSetChanged()
    }


}