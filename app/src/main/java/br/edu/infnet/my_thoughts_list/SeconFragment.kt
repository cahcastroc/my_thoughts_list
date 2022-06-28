package br.edu.infnet.my_thoughts_list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.infnet.my_thoughts_list.viewmodel.RandomThoughtsViewModel

import br.edu.infnet.my_thoughts_list.databinding.FragmentSeconBinding


class SeconFragment : Fragment() {

    private lateinit var viewModel: RandomThoughtsViewModel
    private lateinit var binding: FragmentSeconBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_secon,
            container,
            false
        )

        val name = arguments?.getString("name")
        var takeThought = ""

        binding.tvName.text =" Olá, $name!!"

        viewModel = ViewModelProvider(this).get(RandomThoughtsViewModel::class.java)

        viewModel.thought.observe(viewLifecycleOwner) { newThought ->
            binding.tvRandom.text = newThought
            takeThought = newThought
        }

        binding.btShare.setOnClickListener {
            if(takeThought.isNotBlank()){
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"

                val thoughtShareText = "Pensamento do dia gerado pelo My Thoughts app: ${takeThought}"
                shareIntent.putExtra(Intent.EXTRA_TEXT, thoughtShareText)

                val chooser =
                    Intent.createChooser(shareIntent, "Selecione uma Opção de Compartilhamento")
                this.startActivity(chooser)
            }else{
                Toast.makeText(activity, "Insira um pensamento", Toast.LENGTH_LONG).show()
            }


        }


        binding.btMyList.setOnClickListener {
            val navController = this.findNavController()

            navController.navigate(R.id.action_seconFragment_to_mainActivity)
        }


        return binding.root
    }

}