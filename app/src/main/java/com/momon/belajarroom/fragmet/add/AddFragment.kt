package com.momon.belajarroom.fragmet.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.momon.belajarroom.R
import com.momon.belajarroom.viewmodel.UserViewModel
import com.momon.belajarroom.model.user
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener{
            insertToDatabase()
        }
        return view
    }

    private fun insertToDatabase() {
        val firstName = addFirstname.text.toString()
        val lastName = addLastname.text.toString()

        if(inputChek(firstName,lastName)){
            //Create User Objeck
            val User = user(0,firstName,lastName)
            // Add Data to Database
            mUserViewModel.addUser(User)
            Toast.makeText(requireContext(),"Data Berhasil Di Tambahkan",Toast.LENGTH_LONG).show()
            // Navigasi kembali
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Coba Masukkan Data Kembali",Toast.LENGTH_LONG).show()
        }

    }
    private fun inputChek(firsName : String, lastName : String):Boolean{
        return !(TextUtils.isEmpty(firsName) && TextUtils.isEmpty(lastName))
    }



}