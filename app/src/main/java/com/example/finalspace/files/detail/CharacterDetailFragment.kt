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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalspace.R
import com.example.finalspace.files.Singletons
import com.example.finalspace.files.api.FSApi
import com.example.finalspace.files.list.Character
import com.example.finalspace.files.list.CharacterListAdapter
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream

class CharacterDetailFragment : Fragment() {

    lateinit var textViewName: TextView
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
        imgView = view.findViewById(R.id.imgview_detail_img_url)
        callApi()

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.navigate_to_character_list_fragment)
        }
    }

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
        }




    private fun callApi(){
        val id = arguments?.getInt("characterId")

        Singletons.FSApi.getCharacterDetail("${id}").enqueue(object : Callback<Character>{
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if(response.isSuccessful && response.body()!=null ){
                    val perso: Character = response.body()!!
                    textViewName.text = perso.name.toString()
                    val characPicture : Bitmap = DownloadImageTask(imgView,perso.img_url)
                    if (characPicture!=null) {
                        characPicture.prepareToDraw()
                        onPostExecute(characPicture)
                    }
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
