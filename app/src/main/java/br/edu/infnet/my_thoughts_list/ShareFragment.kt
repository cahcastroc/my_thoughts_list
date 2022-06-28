package br.edu.infnet.my_thoughts_list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class ShareFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_share, container, false)

        val etThoughtShare = view.findViewById<EditText>(R.id.etThoughtShare)

        val btThoughtShare = view.findViewById<Button>(R.id.btThoughtShare)

        btThoughtShare.setOnClickListener {

            if (etThoughtShare.text.isNotBlank()) {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"

                val thoughtShareText = "Olá, estou usando o My Thoughts App e quis compartilhar com você o meu pensamento do dia: ${etThoughtShare.text.toString()}"
                shareIntent.putExtra(Intent.EXTRA_TEXT, thoughtShareText)

                val chooser =
                    Intent.createChooser(shareIntent, "Selecione uma Opção de Compartilhamento")
                this.startActivity(chooser)
            }else{
                Toast.makeText(activity, "Insira um pensamento", Toast.LENGTH_LONG).show()
            }


        }

        return view
    }

}