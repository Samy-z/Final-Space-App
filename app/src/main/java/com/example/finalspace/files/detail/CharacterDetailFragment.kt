package com.example.finalspace.files.detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.finalspace.R
import com.example.finalspace.files.Singletons
import com.example.finalspace.files.list.Character
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

class CharacterDetailFragment : Fragment() {

    lateinit var textViewName: TextView
    lateinit var textViewSpec: TextView
    lateinit var textViewHair: TextView
    lateinit var textViewGend: TextView
    lateinit var textViewOrig: TextView
    lateinit var textViewStat: TextView
    lateinit var imgView: ImageView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewName = view.findViewById(R.id.textview_detail_name)
        textViewSpec = view.findViewById(R.id.textview_detail_species)
        textViewHair = view.findViewById(R.id.textview_detail_hair)
        textViewGend = view.findViewById(R.id.textview_detail_gender)
        textViewOrig = view.findViewById(R.id.textview_detail_origin)
        textViewStat = view.findViewById(R.id.textview_detail_status)
        imgView = view.findViewById(R.id.imgview_detail_img_url)
        callApi()

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.navigate_to_character_list_fragment)
        }
    }
/*      Code de récupération et affichage des images (celui qui était buggé)
    lateinit var bmImage: ImageView

    public fun DownloadImageTask (bmImage: ImageView, url: String) : Bitmap {
        this.bmImage = bmImage
        var imgDL : Bitmap = doInBackground(url)
        imgDL.prepareToDraw()
        return imgDL
    }


    fun doInBackground(url: String) : Bitmap {
        lateinit var mIcon11: Bitmap
        val input: InputStream = java.net.URL(url).openStream()
        mIcon11 = BitmapFactory.decodeStream(input)
        mIcon11.prepareToDraw()
        return mIcon11;
    }

    private fun onPostExecute(result: Bitmap) {
        bmImage.setImageBitmap(result)
    }*/




    private fun callApi(){
        val id = arguments?.getInt("characterId")

        Singletons.FSApi.getCharacterDetail("${id}").enqueue(object : Callback<Character>{
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if(response.isSuccessful && response.body()!=null ){
                    val perso: Character = response.body()!!
                    textViewName.text = perso.name.toUpperCase()
                    textViewSpec.text = ("SPECIES: "+perso.species+"\n").toUpperCase()
                    textViewHair.text = ("HAIR: "+perso.hair+"\n").toUpperCase()
                    textViewGend.text = ("GENDER: "+perso.gender+"\n").toUpperCase()
                    textViewOrig.text = ("ORIGIN: "+perso.origin).toUpperCase()
                    textViewStat.text = ("CURRENT STATUS: "+perso.status).toUpperCase()
                    Picasso.get().load(perso.img_url).into(imgView);

                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {

            }
        })
    }
    fun onClickedCharacter(character: Character) {
        findNavController().navigate(R.id.navigate_to_character_list_fragment)
    }
}
