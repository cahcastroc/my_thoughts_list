package br.edu.infnet.my_thoughts_list.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RandomThoughtsViewModel: ViewModel() {



    private val _thought = MutableLiveData<String>()
    val thought: LiveData<String>
        get() = _thought

    private lateinit var thoughtList: MutableList<String>

    private fun randomList(){
        thoughtList = mutableListOf(
            "Eu vou sempre falar a verdade, não importa as consequências.",
            "Vou pensar antes de agir, mas vou sempre agir.",
            "Ninguém é perfeito, eu vou me perdoar e vou perdoar os outros.",
            "Eu vou definir metas da minha vida, mas jamais vou deixar de viver a vida.",
            "Em vez de reclamar sobre os problemas, eu vou optar por fornecer uma solução para eles.",
            "Meu sorriso traz a felicidade para os que me rodeiam.",
            "Eu não vou ter medo de cometer erros. Eu preciso aprender com meus erros para não repeti-los.",
            "A vida é vazia sem felicidade. Devo fazer o que me faz feliz."
        )
        thoughtList.shuffle()
    }

    init {
        _thought.value =""
        randomList()
        nextThought()
    }

    private fun nextThought() {
        if(thoughtList.isNotEmpty()){
            _thought.value = thoughtList.removeAt(0)
        }
    }
}