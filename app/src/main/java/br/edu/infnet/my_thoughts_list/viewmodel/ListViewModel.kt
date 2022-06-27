package br.edu.infnet.my_thoughts_list.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.edu.infnet.my_thoughts_list.model.AppDatabase
import br.edu.infnet.my_thoughts_list.model.ListEntity
import br.edu.infnet.my_thoughts_list.model.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel ( private val repository: ListRepository) : ViewModel() {

    val allThoughts: LiveData<List<ListEntity>> = repository.allThoughts.asLiveData()


    fun insert (listEntity: ListEntity){  //---Add Item. Acessa o ViewModel Scope para poder acessar o CoroutineScope e assim é possível acessar a base de dados e inserir algo lá
        viewModelScope.launch {
            repository.insert(listEntity)
        }
    }

    fun deleteAll (){    //----Deleta tudo
        viewModelScope.launch {
            repository.deleteAll()
        }
    }



    class ListViewModelFactory (private val repository: ListRepository):
            ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListViewModel::class.java)){
                @Suppress("UNCHECKED CAST")
                return ListViewModel(repository) as T
            } else {
                throw IllegalArgumentException("ViewModel não encontrado")
            }
        }
            }

}