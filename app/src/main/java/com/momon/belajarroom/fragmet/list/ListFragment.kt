package com.momon.belajarroom.fragmet.list

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.momon.belajarroom.R
import com.momon.belajarroom.model.user
import com.momon.belajarroom.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)

            //Recyclerview
        val adapter = listAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //user view model
        mUserViewModel =ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user ->
            adapter.setData(user)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }

        setHasOptionsMenu(true)


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser(){
        val builder = AlertDialog.Builder(requireContext())
        // Judul
        builder.setTitle("Hati-Hati !")
        // Pesan yang di tamopilkan
        builder.setMessage("Apakah Anda ingin Menghapus data ?")
        builder.setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(), "Anda menghapus data", Toast.LENGTH_LONG).show()

        })
        builder.setNegativeButton("Kembali", DialogInterface.OnClickListener { dialogInterface, i ->
            Toast.makeText(requireContext(), "batal", Toast.LENGTH_LONG).show()
        })
            .show()
    }


}