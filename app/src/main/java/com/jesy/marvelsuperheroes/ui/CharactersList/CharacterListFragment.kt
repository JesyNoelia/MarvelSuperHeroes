package com.jesy.marvelsuperheroes.ui.CharactersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jesy.marvelsuperheroes.R
import com.jesy.marvelsuperheroes.databinding.FragmentListBinding
import com.jesy.marvelsuperheroes.domain.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : Fragment(R.layout.fragment_list) {

    private val charactersAdapter by lazy { CharacterListAdapter() }
    private lateinit var binding: FragmentListBinding
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getData()
    }

    private fun getData() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(2){
                viewModel.state.collect{
                    when{
                        it.isLoading->{
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank()->{
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                        }
                        it.characterList!!.isNotEmpty()->{
                            binding.progressBar.visibility = View.GONE
                            charactersAdapter.setData(it.characterList as ArrayList<CharacterModel>)

                        }
                    }
                }
                //delay(1000)
            }
        }
    }


    private fun initView() {
        recyclerView = binding.characterListRV
        layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {

                    viewModel.getAllCharacters()
                    getData()
                }
            }
        })
    }
}