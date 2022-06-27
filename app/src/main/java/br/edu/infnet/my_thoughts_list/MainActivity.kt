package br.edu.infnet.my_thoughts_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.my_thoughts_list.model.ListEntity
import br.edu.infnet.my_thoughts_list.model.ThoughtsListAdapter
import br.edu.infnet.my_thoughts_list.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {


    private val listViewModel: ListViewModel by viewModels {
        ListViewModel.ListViewModelFactory((application as ListApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        val etNewThought = findViewById<EditText>(R.id.etNewThought)
        val adapter = ThoughtsListAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        val btAdd = findViewById<Button>(R.id.btAdd)

        btAdd.setOnClickListener {

            if(etNewThought.text.isNotBlank()) {
                val thought = etNewThought.text.toString()
                val newThought = ListEntity(thoughts = thought)
                listViewModel.insert(newThought)
            }else{
                Toast.makeText(this, "Insira um pensamento", Toast.LENGTH_LONG).show()
            }
        }

        listViewModel.allThoughts.observe(this) { thoughts ->
            thoughts.let { adapter.submitList(it) }
        }
    }
}


