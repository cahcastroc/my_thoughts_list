package br.edu.infnet.my_thoughts_list.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.my_thoughts_list.model.thoughtListApp


class RandomThoughtsViewModel : ViewModel() {


    private val _thought = MutableLiveData<String>()
    val thought: LiveData<String>
        get() = _thought


    fun randomList() {
        _thought.value = thoughtListApp.random()
    }

    init {
        randomList()
    }
}