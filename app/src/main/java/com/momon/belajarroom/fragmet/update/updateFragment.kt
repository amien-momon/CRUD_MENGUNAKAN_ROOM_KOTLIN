package com.momon.belajarroom.fragmet.update

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.momon.belajarroom.R
import com.momon.belajarroom.model.user
import com.momon.belajarroom.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.cutom_row.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class updateFragment : Fragment() {

    private val args by navArgs<updateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateFirstname.setText(args.currentUser.firstName)
        view.updateLastname.setText(args.currentUser.lastName)

        view.update_btn.setOnClickListener{
            updateItem()
        }
       /** view.delete_btn.setOnClickListener{
            deleteUser()
        }*/



        // Tambah Menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val firstname =updateFirstname.text.toString()
        val lastname = updateLastname.text.toString()


        if (inputCheck(firstname,lastname)){
            // Creat user obj
            val updateUser = user(args.currentUser.id, firstname, lastname)
            // Update Current User
            mUserViewModel.updateUser(updateUser)
            //Naigasi kembali
            Toast.makeText(requireContext(),"Data Berhasil Di Perbaharui", Toast.LENGTH_LONG).show()
            // Navigasi kembali
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Masukkan Data ", Toast.LENGTH_LONG).show()
        }


    }

    private fun inputCheck(firsName : String, lastName : String):Boolean{
        return !(TextUtils.isEmpty(firsName) && TextUtils.isEmpty(lastName))
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
       val builder = AlertDialog.Builder(requireContext())
            // Judul
            builder.setTitle("Hati-Hati !")
            // Pesan yang di tamopilkan
            builder.setMessage("Apakah Anda ingin Menghapus data ?")
            builder.setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
                mUserViewModel.deleteUser(args.currentUser)
                Toast.makeText(requireContext(), "Anda menghapus data", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            })
            builder.setNegativeButton("Kembali", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(requireContext(), "batal", Toast.LENGTH_LONG).show()
            })
            .show()
    }
}