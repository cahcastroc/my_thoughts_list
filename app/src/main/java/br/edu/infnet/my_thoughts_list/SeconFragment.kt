package br.edu.infnet.my_thoughts_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class SeconFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_secon, container, false)

        val btMyList = view.findViewById<Button>(R.id.btMyList)

        val btShare = view.findViewById<Button>(R.id.btShare)

        val name = arguments?.getString("name")

        val tvName = view.findViewById<TextView>(R.id.tvName)
        tvName.text = " Olá, $name!!"

        btMyList.setOnClickListener {
            val navController = this.findNavController()

            navController.navigate(R.id.action_seconFragment_to_mainActivity)
        }

        btShare.setOnClickListener {
            val navController = this.findNavController()

            navController.navigate(R.id.action_seconFragment_to_shareFragment)
        }




        return view
    }


}