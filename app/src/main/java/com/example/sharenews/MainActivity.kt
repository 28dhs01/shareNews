package com.example.sharenews

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent


import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter(this)
        recyclerView.adapter= mAdapter
    }
//
//private fun fetchData() {
//
//    val url = "http://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=fb3a86cf7c76428c9d2723a27b049790"
//    val jsonObjectRequest = object : JsonObjectRequest(
//        Request.Method.GET,
//        url,
//        null,
//        Response.Listener {
//            val newsJsonArray = it.getJSONArray("articles")
//            val newsArray = ArrayList<News>()
//            for(i in 0 until newsJsonArray.length()){
//                val newsJsonObject = newsJsonArray.getJSONObject(i)
//                val news = News(
//                    newsJsonObject.getString("title"),
//                    newsJsonObject.getString("author"),
//                    newsJsonObject.getString("url"),
//                    newsJsonObject.getString("urlToImage")
//                )
//                newsArray.add(news)
//            }
//
//            mAdapter.updateNews(newsArray)
//        },
//        Response.ErrorListener {
//
//        }
//    ) {
//
//        override fun getHeaders(): MutableMap<String, String> {
//            val headers = HashMap<String, String>()
//            headers["User-Agent"] = "Mozilla/5.0"
//            return headers
//        }
//
//    }
//
//    MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
//
//
//
//
//
//
//
//
//
//
//    override fun onItemClicked(item: News) {
//        TODO("Not yet implemented")
//        val builder =  CustomTabsIntent.Builder();
//        val customTabsIntent = builder.build();
//        customTabsIntent.launchUrl(this, Uri.parse(item.url));
//
//    }
//}
private fun fetchData() {
    //volly library
    val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=5f526ba3bd954661a76a4831e47c0859"
    //making a request
    val jsonObjectRequest = object: JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        Response.Listener {
            val newsJsonArray = it.getJSONArray("articles")
            val newsArray = ArrayList<News>()
            for(i in 0 until newsJsonArray.length()) {
                val newsJsonObject = newsJsonArray.getJSONObject(i)
                val news = News(
                    newsJsonObject.getString("title"),
                    newsJsonObject.getString("author"),
                    newsJsonObject.getString("url"),
                    newsJsonObject.getString("urlToImage")
                )
                newsArray.add(news)
            }

            mAdapter.updateNews(newsArray)
        },
        Response.ErrorListener {
        }

    ) {
        override fun getHeaders(): MutableMap<String, String> {
            val headers = HashMap<String, String>()
            headers["User-Agent"] = "Mozilla/5.0"
            return headers
        }
    }

    MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
}

    override fun onItemClicked(item: News) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
    }



