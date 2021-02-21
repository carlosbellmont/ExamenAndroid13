package com.cbellmont.ejemplodescargainternet

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception


class GetAllFilms {
    companion object {
        suspend fun send() : List<Film> = withContext(Dispatchers.IO){

            val client = OkHttpClient()
            val url = "https://swapi.dev/api/films/"
            val request = Request.Builder()
                .url(url)
                .build()


            val call = client.newCall(request)
            try {
                val response =  call.execute()
                val bodyInString = response.body?.string()
                bodyInString?.let {
                    val JsonObject = JSONObject(bodyInString)

                    val results = JsonObject.optJSONArray("results")
                    results?.let {
                        val gson = Gson()

                        val itemType = object : TypeToken<List<Film>>() {}.type

                        return@withContext gson.fromJson(results.toString(), itemType)
                    }
                }
            } catch (ex : Exception) {
                Log.e("TAG", "Ups, algo ha ido mal")
            }
            listOf()
        }
    }
}