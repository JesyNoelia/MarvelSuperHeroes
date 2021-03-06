package com.jesy.marvelsuperheroes.ui.CharactersList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.jesy.marvelsuperheroes.domain.model.CharacterModel
import com.jesy.marvelsuperheroes.domain.usecases.CharactersUseCase
import com.jesy.marvelsuperheroes.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
) : ViewModel() {
    val characterListLiveData = MutableLiveData<List<CharacterModel>>()
    var isLoading = MutableLiveData<Boolean>()
    private var superheroList: List<CharacterModel> = emptyList()
    val error = MutableLiveData<String>()

    private val _state = MutableStateFlow(MarvelListState())
    val state: StateFlow<MarvelListState> = _state.asStateFlow()

    fun getAllCharacters()= viewModelScope.launch(Dispatchers.IO) {
        charactersUseCase().collect {
            when(it){
                is Response.Success -> {
                    _state.value = MarvelListState(characterList = it.data?: emptyList())
                }
                is Response.Loading -> {
                    _state.value = MarvelListState(isLoading = true)
                }
                is Response.Error -> {
                    _state.value = MarvelListState(error = it.message?: "An error occurred")

                }
            }
        }
    }
}