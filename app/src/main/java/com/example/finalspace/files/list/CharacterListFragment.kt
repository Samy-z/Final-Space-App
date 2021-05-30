package com.example.finalspace.files.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalspace.R
import com.example.finalspace.files.Singletons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CharacterListFragment : Fragment() {



    private lateinit var recyclerView: RecyclerView
    private val adapter = CharacterListAdapter(arrayListOf(),::onClickedCharacter)
    private val layoutManager = LinearLayoutManager(context)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.character_recyclerview)

        adapter.listener
        recyclerView.apply {
            layoutManager = this@CharacterListFragment.layoutManager
            adapter = this@CharacterListFragment.adapter
        }

        val characListCall: Call<List<Character>> = Singletons.FSApi.getCharacterList()
        characListCall.enqueue(object : Callback<List<Character>> {
            override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                if(response.body()==null){
                    var characTab: List<Character> = arrayListOf<Character>().apply {
                        add(Character(998,"Error, get response null", "human","","","",""))
                        response.errorBody().toString()
                    }
                    adapter.updateList(characTab)
                }else{
                    var characTab = mutableListOf<Character>()
                    for (c in response.body()!!) {
                        characTab.add(c)
                    }
                    adapter.updateList(characTab.toList())
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                var characTab: List<Character> = arrayListOf<Character>().apply {
                    add(Character(999,"Request failed", "","","","",""))
                }
                adapter.updateList(characTab)
            }
        })


    }
    fun onClickedCharacter(id: Int) {
        findNavController().navigate(R.id.navigate_to_character_detail_fragment, bundleOf(
                "characterId" to id
        ))
    }


}

