package com.jesy.marvelsuperheroes.ui.CharactersList

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jesy.marvelsuperheroes.R
import com.jesy.marvelsuperheroes.databinding.ItemCardBinding
import com.jesy.marvelsuperheroes.domain.model.CharacterModel
import com.jesy.marvelsuperheroes.util.Constants.Companion.DOT
import com.jesy.marvelsuperheroes.util.Constants.Companion.IMAGE_LARGE_SIZE
import com.jesy.marvelsuperheroes.util.inflate
import com.jesy.marvelsuperheroes.util.loadUrl

class CharacterListAdapter() : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    internal var characterListener: (CharacterModel)-> Unit = {}
    var characters: MutableList<CharacterModel> = ArrayList()
    lateinit var context: Context


    fun CharactersAdapter(characters: MutableList<CharacterModel>, context: Context){
        this.characters = characters
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterListViewHolder(
        parent.inflate(
            R.layout.item_card
        )
    )


    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        holder.bind(characters[position], characterListener)
    }

    override fun getItemCount() = characters.size

    inner class CharacterListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemCardBinding.bind(itemView)

        fun bind(item: CharacterModel, characterListener: (CharacterModel)-> Unit){
            binding.tvName.text = item.name
            binding.cover.loadUrl( "${item.thumbnail}${IMAGE_LARGE_SIZE}${DOT}${item.thumbnailExt}")
            item.thumbnail?.let { binding.cover.loadUrl(it) }
            binding.cardView.setOnClickListener{
                characterListener(item)
            }
        }

    }

    fun setData(characterList: ArrayList<CharacterModel>){
        this.characters.addAll(characterList)
        notifyDataSetChanged()
    }
}