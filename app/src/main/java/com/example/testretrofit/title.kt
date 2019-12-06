package com.example.testretrofit


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.testretrofit.databinding.FragmentTitleBinding
import com.example.testretrofit.databinding.RowLayoutBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_title.*
import kotlinx.android.synthetic.main.row_layout.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL



/**
 * A simple [Fragment] subclass.
 */
class title : Fragment() {

    val list=ArrayList<MyData>()
    val adapter=ListAdapter(this,list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_title, container, false)
//        val binding = DataBindingUtil.inflate<RowLayoutBinding>(inflater,
//            R.layout.fragment_title,container,false)

//
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url ="https://api.github.com/users"
        AsyncTaskHandler().execute(url)
    }

    inner class AsyncTaskHandler: AsyncTask<String, String, String>(){

        override fun doInBackground(vararg url: String?): String {
            val res:String
            val connection= URL(url[0]).openConnection()as HttpURLConnection
            try{
                connection.connect()
                res=connection.inputStream.use { it.reader().use { reader->reader.readText() } }
            }finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            jsonResult(result)
        }
        private fun jsonResult(jsonString:String?){
            val jsonArray= JSONArray(jsonString)
            var i=0
            while (i<jsonArray.length()){
                val jsonObject=jsonArray.getJSONObject(i)
                list.add(
                    MyData(
                        jsonObject.getString("login"),
                        jsonObject.getInt("id"),
                        jsonObject.getString("node_id"),
                        jsonObject.getString("avatar_url"),
                        jsonObject.getString("gravatar_id"),
                        jsonObject.getString("url"),
                        jsonObject.getString("html_url"),
                        jsonObject.getString("followers_url"),
                        jsonObject.getString("following_url"),
                        jsonObject.getString("gists_url"),
                        jsonObject.getString("starred_url"),
                        jsonObject.getString("subscriptions_url"),
                        jsonObject.getString("organizations_url"),
                        jsonObject.getString("repos_url"),
                        jsonObject.getString("events_url"),
                        jsonObject.getString("received_events_url"),
                        jsonObject.getString("type"),
                        jsonObject.getString("site_admin")
                    )
                )
                i++
            }

            mylist.adapter=adapter
        }
    }
}

