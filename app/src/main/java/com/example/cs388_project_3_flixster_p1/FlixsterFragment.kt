package com.example.cs388_project_3_flixster_p1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to Flixster+ API.
 */

class FlixsterFragment(override val progressBar: Any) : Fragment(), OnListFragmentInteractionListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_now_playing_movielist, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        val apiKey = API_KEY
        val client = OkHttpClient()

        val urlBuilder = HttpUrl.Builder()
            .scheme("https")
            .host("api.themoviedb.org")
            .addPathSegments("3/movie/now_playing")
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("language", "en-US") // You can change the language code
            .addQueryParameter("page", "1") // Page number

        val url = urlBuilder.build()

        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("accept", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body

                if (response.isSuccessful) {
                    // The wait for a response is over
                    progressBar.hide()

                    // Parse JSON into Models
                    val gson = Gson()
                    val arrayBookType = object : TypeToken<List<Movies>>() {}.type
                    val jsonResponse = responseBody?.string()

                    if (!jsonResponse.isNullOrEmpty()) {
                        val models: List<Movies> = gson.fromJson(jsonResponse, arrayBookType)
                        recyclerView.adapter = MoviesRecyclerViewAdapter(models, this@FlixsterFragment)
                    } else {
                        // Handle empty or invalid JSON
                    }
                    // Look for this in Logcat:
                    Log.d("BestSellerBooksFragment", "response successful")
                } else {
                    // Handle API error
                }
                responseBody?.close()
            }
            override fun onFailure(
                call: Call,
                e: IOException
            ) {
                // The wait for a response is over
                progressBar.hide()

                // Handle failure, log error, etc.
                Log.e("BestSellerBooksFragment", "Failed to retrieve data", e)
            }
        })
    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: Movies) {
        Toast.makeText(context, "Clicked: " + item.title, Toast.LENGTH_LONG).show()
    }

}